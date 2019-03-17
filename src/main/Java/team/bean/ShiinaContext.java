package team.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

//框架的上下文
public class ShiinaContext {

    private HttpServletRequest request;
    private HttpServletResponse response;

    //参数 map
    private Map<String,String> paramMap;

    private RouteInfo routeInfo;  //路由信息

    public HttpServletRequest getRequest(){
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public RouteInfo getRouteInfo() {
        return routeInfo;
    }

    public ShiinaContext(HttpServletRequest request,HttpServletResponse response,RouteInfo routeInfo){
        this.response = response;
        this.request = request;
        this.routeInfo = routeInfo;
        this.paramMap = new HashMap<>();  //这里构造器创建了 Hashmap
    }

    //putParam 可以 放置 参数
    public void putParam(String key,String value){
        this.paramMap.put(key,value);
    }

    //getParam 根据键，获取值
    public String getParam(String key){
        return this.paramMap.get(key);
    }
}
