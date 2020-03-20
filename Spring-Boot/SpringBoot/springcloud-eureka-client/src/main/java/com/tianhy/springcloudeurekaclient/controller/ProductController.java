package com.tianhy.springcloudeurekaclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tianhy.springcloudeurekaclient.pojo.UserPo;
import com.tianhy.springcloudeurekaclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/22
 **/
@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    //注入Feign接口
    @Autowired
    private UserService service;

    //ribbon 的方式调用用户服务
    @GetMapping("/ribbon")
    public UserPo testRibbon() {
        UserPo user = null;
        int count = 10;
        for (int i = 0; i < count; i++) {
            //USER 为用户服务
            user = restTemplate.getForObject("http://USER/user/" + (i + 1), UserPo.class);
        }
        return user;
    }

    //ribbon熔断测试
    @GetMapping("/circuitBreaker")
    @HystrixCommand(fallbackMethod = "error")
    public String circuitBreaker() {
        return restTemplate.getForObject("http://USER/timeout", String.class);
    }

    //通过Feign调用用户服务
    //获取用户信息
    @GetMapping("/feign")
    public UserPo testFeign() {
        UserPo user = null;
        int count = 10;
        for (int i = 0; i < count; i++) {
            Long id = (long) (i + 1);
            user = service.getUerPo(id);
        }
        return user;
    }

    //插入用户信息
    @GetMapping("/feign/insert")
    public Map<String, Object> insetUer() {
        Map<String, Object> map = null;
        UserPo user = null;
        int count = 10;
        for (int i = 0; i < count; i++) {
            user = new UserPo();
            Long id = (long) (i + 1);
            int level = (i % 3) + 1;
            user.setId(id);
            user.setLevel(level);
            user.setUserName("user_name_" + i);
            user.setNote("note_" + i);
            map = service.insertUser(user);
        }
        return map;
    }

    //更新用户名称
    @GetMapping("/feign/update")
    public Map<String, Object> update() {
        Map<String, Object> map = null;
        int count = 10;
        for (int i = 0; i < count; i++) {
            Long id = (long) (i + 1);
            String userName = "user_name_" + id;
            map = service.updateUser(userName, id);
        }
        return map;
    }

    //feign熔断测试
    @GetMapping("/circuitBreaker1")
    @HystrixCommand(fallbackMethod = "error")
    public String circuitBreaker1() {
        return service.testTimeout();
    }

    //自定义熔断超时时间
    @GetMapping("/circuitBreaker2")
    @HystrixCommand(fallbackMethod = "error",commandProperties ={
            @HystrixProperty(name = "execution.isolation.timeoutInMilliseconds",value = "3000")
    })
    public String circuitBreaker2() {
        return service.testTimeout();
    }

    //服务降级方法
    public String error() {
        return "服务调用超时,请稍后重试";
    }
}
