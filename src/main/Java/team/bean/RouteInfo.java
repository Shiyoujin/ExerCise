package team.bean;

import team.annotation.HttpMethod;

import java.util.Objects;

//路由信息
public class RouteInfo {

    private String uri;

    private HttpMethod method; //之前是 枚举类

    private String uriParam;

    public String getUriParam() {
        return uriParam;
    }

    public void setUriParam(String uriParam) {
        this.uriParam = uriParam;
    }

    public String getUri() {
        return uri;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public RouteInfo(HttpMethod method,String uri) {
        this.uri = uri;
        this.method = method;
    }

    //TODO
    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        RouteInfo routeInfo = (RouteInfo) o;
        return Objects.equals(uri,routeInfo.uri) &&
                method == routeInfo.method;   //??

    }

    @Override
    public int hashCode() {
        return Objects.hash(uri,method);
    }

}
