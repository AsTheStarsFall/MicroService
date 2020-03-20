package com.tianhy.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: thy
 * @Date: 2020/2/23 2:15
 * @Desc:
 */
@RestController
public class TestController {

    @GetMapping("/add")
    public String add(Integer a, Integer b, HttpServletRequest request) {
        int fromPort = request.getServerPort();
        return "From port :" + fromPort + "," + "Result is :" + (a + b);
    }
}
