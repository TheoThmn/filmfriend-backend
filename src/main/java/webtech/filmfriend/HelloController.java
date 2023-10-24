package webtech.filmfriend;

import org.springframework.web.bind.annotation.*;

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
    public String userPath(@PathVariable int userid, @PathVariable String password) {
        return "Hello User! Your ID is " + userid + "! Your password is " + password + "!";
    }

    @GetMapping("/user")
    public String userQuery(@RequestParam int userid, @RequestParam String password) {
        return "Hello User! Your ID is " + userid + "! Your password is " + password + "!";
    // http://localhost:8080/user?password=pass&userid=1234
    }

    @GetMapping("/user-agent")
    public String userAgent(@RequestHeader("User-Agent") String userAgent) {
        return "Hello User! Your User-Agent is " + userAgent + "!";
    // http://localhost:8080/user-agent
    }
}