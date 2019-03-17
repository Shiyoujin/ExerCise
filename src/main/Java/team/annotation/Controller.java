package team.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component   //不属于@Controller、@Services等的时候），我们就可以使用@Component来标注这个类。
public @interface Controller {
}
