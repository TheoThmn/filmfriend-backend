package webtech.filmfriend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Greetings from filmfriend!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/user/{userid}/password/{password}")
    public String user(@PathVariable int userid, @PathVariable String password) {
        return "Hello User! Your ID is " + userid + "! Your password is " + password + "!";
    }
}