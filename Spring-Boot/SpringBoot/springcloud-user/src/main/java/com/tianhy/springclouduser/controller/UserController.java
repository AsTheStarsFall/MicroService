package com.tianhy.springclouduser.controller;

import com.tianhy.springclouduser.pojo.UserPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/22
 **/
@Slf4j
@RestController
public class UserController {

    //服务发现客户端
    @Autowired
    private DiscoveryClient discoveryClient;


    //获取用户信息
    @GetMapping("/user/{id}")
    public UserPo getUserPo(@PathVariable Long id) {
        ServiceInstance instance = discoveryClient.getInstances("USER").get(0);
        //用户服务ID，服务主机，服务主机端口
        log.info("【{}】: {} : {}", instance.getServiceId(), instance.getHost(), instance.getPort());

        UserPo user = new UserPo();
        user.setId(id);
        int level = (int) (id % 3 + 1);
        user.setLevel(level);
        user.setUserName("user_name_" + id);
        user.setNote("note_" + id);
        return user;
    }

    //新增用户，POST请求
    @PostMapping("/insert")
    public Map<String, Object> insertUser(@RequestBody UserPo userPo) {
        log.info("插入用户信息：{}", userPo.getUserName());

        Map<String, Object> map = new HashMap<>();
        map.put("seccess", true);
        map.put("message", "插入用户{" + userPo.getUserName() + "}信息成功");
        return map;
    }

    //更新用户名，用户编号使用请求头的形式
    @PostMapping("/update/{userName}")
    public Map<String, Object> updateUser(@PathVariable String userName, @RequestHeader("id") Long id) {
        log.info("更新用户");
        //
        Map<String, Object> map = new HashMap<>();
        map.put("seccess", true);
        map.put("message", "更新用户{id:" + id + ",userName:" + userName + "}名称成功");
        return map;
    }

    //SprngCloud中断路器是由Hystrix实现的，默认服务之间调用超时时间为2000ms
    //断路器熔断测试
    @GetMapping("/timeout")
    public String timeOut() {
        //随机3000以内的数字
        long ms = (long) (3000L * Math.random());
        try {
            //如果超时时间大于默认的2000ms，则出现断路，进入降级方法
            long start = System.currentTimeMillis();
            Thread.sleep(ms);
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "熔断测试";
    }
}
