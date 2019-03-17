package team.util;

import com.sun.istack.internal.NotNull;

//转化工具类
public class CastUtil {
    //将 字符串 转换为 int值
    //text 为要转化的 字符串
    //return 转换完的 int值，字符串不合法的情况下为0
    public static int castInt(@NotNull String text){
        return castInt(text,0);
    }

    //将字符串转化为 int值，如果字符串不合法，返回一个 预设值
    //define 为预设的值即 为 0
    public static int castInt(@NotNull String text,@NotNull int define){
        int res;
        try {
            res = Integer.parseInt(text);
        } catch (NumberFormatException e){
            res = define;
        }
        return res;
    }

    //字符串转化为long  不合法为 0
    public static long castLong(@NotNull String text){
        return castLong(text,0);
    }

    public static long castLong(String text,long define){
        long result;
        try {
            result = Long.parseLong(text);
        } catch (NumberFormatException e) {
            result = define;
        }
        return result;
    }

}
