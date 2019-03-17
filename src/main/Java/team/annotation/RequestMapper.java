package team.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// METHOD:用于描述方法  TYPE:用于描述类、接口(包括注解类型) 或enum声明
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface RequestMapper {
    String value();
    HttpMethod method() default HttpMethod.GET;
}
