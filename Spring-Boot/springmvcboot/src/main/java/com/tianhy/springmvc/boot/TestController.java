package com.tianhy.springmvc.boot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/14
 **/
@Controller
//spring boot 自动装配
@EnableAutoConfiguration
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public Map<String,String> test(){
        Map<String,String> map = new HashMap<>();
        map.put("key","value");
        return map;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestController.class,args);
    }
}
