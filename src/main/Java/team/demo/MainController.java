package team.demo;



import team.annotation.Autowried;
import team.annotation.Controller;
import team.annotation.HttpMethod;
import team.annotation.RequestMapper;
import team.bean.ResponseEntity;
import team.bean.ShiinaContext;


@Controller
public class MainController {

    @Autowried
    private MainService mainService;

    @RequestMapper(value = "/hello")
    public ResponseEntity hello(ShiinaContext context) {
        return new ResponseEntity(10000, "hello " + context.getParam("name"));
    }

    @RequestMapper(value = "/world")
    public ResponseEntity world(ShiinaContext context) {
        return new ResponseEntity(10000, "hello world!");
    }

    @RequestMapper(value = "/world", method = HttpMethod.POST)
    public ResponseEntity postWorld(ShiinaContext context) {
        return mainService.yoyoyo();
    }
}
