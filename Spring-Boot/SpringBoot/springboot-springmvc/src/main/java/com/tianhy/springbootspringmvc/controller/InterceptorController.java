package com.tianhy.springbootspringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * {@link}
 *
 * @Desc: 拦截控制
 * @Author: thy
 * @CreateTime: 2019/5/22
 **/
@RequestMapping("/interceptor")
@Controller
public class InterceptorController {

    @GetMapping("/start")
    public String start(){
        System.out.println("访问被限制的路径");
        return "/index";
    }
}
