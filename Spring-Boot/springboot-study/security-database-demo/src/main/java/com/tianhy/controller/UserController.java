package com.tianhy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/5 2:23
 **/
@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello()    {
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
