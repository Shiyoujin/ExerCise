package team.core;


import com.google.gson.Gson;
import team.bean.ResponseEntity;
import team.bean.ShiinaContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

//封装过的 处理器
public class Handle {

    //全局 统一的 Json 处理库

    //GSON是Google提供的 用来在Java对象和JSON数据之间进行映射的Java类库。
    // 可以将一个Json字符转成一个Java对象，或者将一个Java转化为Json字符串。
    private static final Gson GSON = new Gson();

    //用来 处理请求的 方法
    private Method method;

    //该 方法所属的 controller
    private Object controller;

    Handle(Object controller,Method method){
        this.controller = controller;
        this.method = method;
    }

    //根据上下文 执行方法
    //参数 context 框架的上下文 返回 如果有返回，那么输出到 网络流
    String invoke(ShiinaContext context) {
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();

        //TODO 获取请求的 所有参数？
        Enumeration<String> params = request.getParameterNames();

        while(params.hasMoreElements()){
            String key = params.nextElement();
            String value = request.getParameter(key);
            context.putParam(key,value);
        }

        //TODO res 返回的 数据类
        ResponseEntity res = null;
        try {
            res = (ResponseEntity) method.invoke(controller,context);
        } catch (IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
        if (res != null){
            return GSON.toJson(res);
        }
        return null;
    }
}
