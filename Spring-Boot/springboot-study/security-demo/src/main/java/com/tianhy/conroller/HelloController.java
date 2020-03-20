package com.tianhy.conroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thy
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    @GetMapping("/admin/db/hello")
    public String admin2() {
        return "/admin/db/hello";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "hello user!";
    }

    @GetMapping("/db/hello")
    public String dba() {
        return "hello dba!";
    }
}
