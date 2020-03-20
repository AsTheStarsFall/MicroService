package com.tianhy.springbootjms.controller;

import com.tianhy.springbootjms.pojo.Result;
import com.tianhy.springbootjms.pojo.User;
import com.tianhy.springbootjms.service.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
@RequestMapping("/rabbitmq")
@Controller
public class RabbitMqController {

    @Autowired
    private RabbitMqService mqService = null;

    @RequestMapping("/msg")
    @ResponseBody
    public Result msg(String msg){
        mqService.sendMsg(msg);
        return Result.success().add("message",msg);
    }

    @RequestMapping("/user")
    @ResponseBody
    public Result user(Long id,String userName,String note){
        User user = new User(id,userName,note);
        mqService.sendUser(user);
        return Result.success().add("message",user);
    }
}
