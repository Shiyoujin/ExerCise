package team.core;

import team.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

// 类加载器
public class ClassLoader {

    //单例
    private static ClassLoader singleton;

    //包下面所有类的集合
    private Set<Class<?>> classSet;

    //只要是 两个及以上的常量 前缀都是 这样的设计
    //url协议类型 为 file
    private static final String PROTOCOL_TYPE = "file";

    private static final String PACKAGE_NAME = "packageName";

    private static final String POINT = ".";

    private static final String END_WITH_CLASS = ".class";

    private static final String EMPTY_STRING = "";

    //获得单例
    public static ClassLoader getInstance(){
        if (singleton == null){
            synchronized(ClassLoader.class){
                if (singleton == null){
                    singleton = new ClassLoader(PropsLoader.getInstance());
                }
            }
        }
        return singleton;
    }

    //不允许 任何方式调用 无参构造器
    //抛出 IllegalAccessException 异常
    private ClassLoader() throws IllegalAccessException{
        throw new IllegalAccessException();
    }

    private ClassLoader(PropsLoader propsLoader){
        load(propsLoader);
    }

    //包扫描
    //参数 propsLoader 配置加载器
    private void load(PropsLoader propsLoader){
        classSet = new HashSet<>();

        String packageName = propsLoader.getString(PACKAGE_NAME);

        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.","/"));
            while (resources.hasMoreElements()){
                URL target = resources.nextElement();

                //protocol URL 的协议
                String protocol = target.getProtocol();
                if (protocol.equalsIgnoreCase(PROTOCOL_TYPE)) {
                    String packagePath = target.getPath();
                    loadClass(packageName,packagePath);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Set<Class<?>> getClassSet() {
        return this.classSet;
    }

    private void loadClass(String packageName,String packagePath){
        File[] files = new File(packagePath).listFiles(file ->file.isDirectory() || file.getName().endsWith(END_WITH_CLASS));
        if (files != null){
            for (File file : files){
                String fileName = file.getName();
                if (file.isDirectory()){
                    String subPackageName = packageName + POINT + fileName;
                    String subPackagePath = packagePath + File.pathSeparator + fileName;
                    loadClass(subPackageName,subPackagePath);
                } else {
                    Class<?> clazz = null;
                    try {
                        String className = StringUtil.append(packageName,POINT,fileName).replaceAll(END_WITH_CLASS,EMPTY_STRING);
                        clazz = Class.forName(className);
                    } catch (ClassNotFoundException e){
                        e.printStackTrace();
                    }
                    if (clazz != null){
                        classSet.add(clazz);
                    }
                }
            }
        }
    }

}
