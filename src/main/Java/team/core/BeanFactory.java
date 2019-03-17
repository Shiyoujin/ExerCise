package team.core;

import team.annotation.Autowried;
import team.annotation.Component;
import team.demo.MainController;
import team.util.ReflectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class BeanFactory {

    //单例模式的单例对象
    private static BeanFactory beanFactory;

    //类加载器
    private ClassLoader classLoader;

    //组件集合 即所有含 @Controller注解和@Component注解的类
    private Set<Class<?>> componentSet;

    //用来给 controller类和对象 提供映射的集合
    private Map<Class<?>,Object> controllerMap;

    //同上 普通component  映射
    private Map<Class<?>,Object> componentMap;


    //获得单例
    //@return 类的单例 --单例模式的常用用法
    public static BeanFactory getInstance() {
        if (beanFactory ==null){
            synchronized (BeanFactory.class){
                if (beanFactory == null){
                    beanFactory = new BeanFactory();
                }
            }
        }
        return beanFactory;
    }

    //获取 controllerMap 控制器的集合
    Map<Class<?>,Object> getControllerMap(){
        return controllerMap;
    }

    private BeanFactory() {
        componentMap = new HashMap<>();
        controllerMap = new HashMap<>();
        componentSet = new HashSet<>();
        classLoader = ClassLoader.getInstance();
        init();
    }
    //TODO

    //初始化 创建所有类 Controller 和 Component的对象
    private void init() {
        Set<Class<?>> classSet = classLoader.getClassSet();  //TODO
        //先加载类的集合，创建映射
        for (Class<?> clazz : classSet) {  //这里用 clazz  避免关键字的麻烦
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> an = annotation.annotationType();
                if (an.equals(Component.class) || an.isAnnotationPresent(Component.class)) {
                    Object obj = ReflectUtil.newInstance(clazz);  //TODO
                    componentMap.put(clazz, obj);
                    componentSet.add(clazz);
                    if (!an.equals(Component.class)) {
                        controllerMap.put(clazz, obj);
                    }
                }
            }
        }

        //再对每个 component里的 成员参数 赋值
        for (Class<?> clazz : componentSet) {   //TODO  -Autowried 是啥?
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields){
                Autowried autowried = field.getAnnotation(Autowried.class);
                if (autowried !=null){
                    Object target = componentMap.get(field.getType());
                    if (target !=null){
                        Object obj = componentMap.get(clazz);
                        ReflectUtil.setFieldValue(field,obj,target);
                    } else {
                        throw new RuntimeException("this component '" + field.getName() + "' is underfined");
                    }
                }
            }
        }
    }

    //获得 Bean 实体
    public <T> T getBean(Class<T> clazz){
        T t = (T) componentMap.get(clazz);
        if (t != null){
            return t;
        }
        throw new RuntimeException("bean not found");
    }

    public static void main(String[] args) {
        System.out.println(MainController.class.getDeclaredAnnotation(Component.class));
    }

}
