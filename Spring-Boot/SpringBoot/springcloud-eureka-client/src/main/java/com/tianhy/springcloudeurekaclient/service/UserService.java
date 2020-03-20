package com.tianhy.springcloudeurekaclient.service;

import com.tianhy.springcloudeurekaclient.pojo.UserPo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/22
 **/
//指定服务ID
@FeignClient("user")
public interface UserService {
    //只是声明调用接口
    //获取用户信息
    @GetMapping("/user/{id}")
    public UserPo getUerPo(@PathVariable Long id);

    //插入用户信息
    @PostMapping("/insert")
    public Map<String,Object> insertUser(UserPo userPo);

    //更新用户名
    @PostMapping("/update/{userName}")
    public Map<String,Object> updateUser(@PathVariable String userName,@RequestHeader Long id);

    //熔断测试
    @GetMapping("/timeout")
    public String testTimeout();

}
