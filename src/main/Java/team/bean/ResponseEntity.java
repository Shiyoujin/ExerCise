package team.bean;

//返回的数据类
public class ResponseEntity<T> {
    int code;
    String info;
    T data;  //泛型编程

    //Entity 实体的意思
    public ResponseEntity(int code,String info){
        this.code = code;
        this.info = info;
    }

    public ResponseEntity(int code,String info,T t){
        this.code = code;
        this.info = info;
        this.data = t;
    }

}
