package com.tianhy.study.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/1/20 19:14
 **/
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello(String info) {
        System.out.println("come in hello");
        return info;
    }
}
