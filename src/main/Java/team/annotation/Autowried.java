package team.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  //定义 Annotation被保留的时间长短，此处为 运行时保留
@Target(ElementType.FIELD)   //用于描述作用域
public @interface Autowried {
}
