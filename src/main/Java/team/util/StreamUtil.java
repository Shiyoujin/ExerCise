package team.util;


import java.io.*;

//流 工具类
public class StreamUtil {

    //将 数据 写入 输出流
    // 参数 out 输出流 text 要写入的 内容
    public static void writeStream(OutputStream out,String text){
        //包装 流
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(out)
        );

        try {
            writer.write(text);
            writer.flush();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //从 输入流读数据
    //参数 in 输入流 返回 文本数据
    public static String readStream(InputStream in){

        String res = null;

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        in
                )
        );

        StringBuilder sb = new StringBuilder();
        String line;

        try {
            //一行行的 读
            while ((line = reader.readLine()) !=null){
                sb.append(line);
            }
            res = sb.toString();
        } catch (IOException e){
            e.printStackTrace();
        }
        return res;
    }
}
