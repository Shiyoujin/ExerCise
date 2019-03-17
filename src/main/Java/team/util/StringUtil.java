package team.util;

//字符串工具类
public class StringUtil {

    //判断 字符串是否为空
    //参数 text 字符串, 返回 boolean
    public static boolean isEmpty(String text){
        //"" 引用为空字符串，它有地址，它是被实例化的对象，值为空而已。
        //null 引用为空，它没有地址，它是一个没有被实例化的对象
        return text ==null || "".equals(text);   //判断是否为 两种为空的 方式
    }

    //将 字符串数组 连接为 字符串
    //参数 String类型 的数组 array 字符串数组,返回 字符串
    public static String append(String ...array){
        StringBuilder sb = new StringBuilder();
        for (String str : array){
            sb.append(str);
        }
        return sb.toString();
    }
}
