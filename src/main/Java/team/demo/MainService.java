package team.demo;

import team.annotation.Component;
import team.bean.ResponseEntity;

//测试用 service
@Component
public class MainService {
    public ResponseEntity yoyoyo(){
        return new ResponseEntity(10000,"hello world!(post)");
    }
}
