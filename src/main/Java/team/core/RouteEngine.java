package team.core;


import team.annotation.HttpMethod;
import team.annotation.RequestMapper;
import team.bean.RouteInfo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//路由引擎
public class RouteEngine {
    private static RouteEngine instance = null;
    private BeanFactory beanFactory = null;

    //用来存放 method和 uri对处理器的映射
    private Map<RouteInfo,Handle> routeMap;

    //单例模式
    public static RouteEngine getInstance(){
        if (instance == null){
            synchronized (RouteEngine.class){
                if (instance == null){
                    instance = new RouteEngine();
                }
            }
        }
        return instance;
    }

    //调用 构造器 默认 调用下面 init 初始化的方法
    private RouteEngine(){
        init();
    }

    private void init(){
        beanFactory = BeanFactory.getInstance();
        routeMap = new HashMap<>(20);
        loadRoute();
    }

    //TODO 加载路由
    private void loadRoute() {
        Map<Class<?>,Object> controllerMap = beanFactory.getControllerMap();
        String prefix = "";

        //遍历
        for (Map.Entry<Class<?>,Object> entry : controllerMap.entrySet()){
            Class<?> clazz = entry.getKey();
            Object controller = entry.getValue();

            //RequestMapper 注解
            RequestMapper controllerMapper = clazz.getAnnotation(RequestMapper.class);
            if (controllerMapper != null){
                controllerMapper.value();
                prefix = controllerMapper.value();
            }

            Method[] methods = clazz.getMethods();
            for (Method method : methods){
                RequestMapper mapper = method.getAnnotation(RequestMapper.class);
                if (mapper != null){

                    String uri = mapper.value();
                    HttpMethod httpMethod = mapper.method();
                    RouteInfo route = new RouteInfo(httpMethod,prefix + uri);
                    Handle handle = new Handle(controller,method);
                    routeMap.put(route,handle);
                }
            }
        }
    }

    public Handle getHandle(RouteInfo route){
        return routeMap.get(route);
    }

}
