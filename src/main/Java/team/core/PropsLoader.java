package team.core;

import team.util.CastUtil;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//配置加载器
public class PropsLoader {

    private static PropsLoader singleton;

    private Properties properties;

    //需要设置 配置文件
    private static final String PROPS_PATH = "/WEB-INF/application.properties";

    public static PropsLoader getInstance() {
        if (singleton != null){
            return singleton;
        }
        throw new RuntimeException("PropsLoader null");
    }

    //初始化 配置
    //参数 context servlet 上下文
    public static void init(ServletContext context){
        if (singleton == null){
            synchronized (PropsLoader.class){
                if (singleton==null){
                    singleton=new PropsLoader(context);
                }
            }
        }
    }

    //不允许使用热河方式调用无参构造器 --抛出异常
    private PropsLoader() throws IllegalAccessException{
        throw new IllegalAccessException();
    }

    //配置文件的 读取和加载
    private PropsLoader(ServletContext context){
        InputStream in = context.getResourceAsStream(PROPS_PATH); //这个 常量地址 前面有
        if (in != null){
            properties = new Properties();
            try {
                properties.load(in);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("application.properties Not Found");
        }
    }

    //获取配置文件 信息
    public String getString(String key){
        String res;
        if (key != null){
            res = properties.getProperty(key);
            if (res != null){
                return res;
            }
            return "";
        }
        throw new RuntimeException("properties " + key + "not found");
    }

    //利用 CastUtil 工具 转化为 int类型进行操作
    public int getInt(String key){
        return CastUtil.castInt(getString(key),0);
    }

    public long getLong(String key){
        return CastUtil.castLong(getString(key),0L);
    }

}
