package com.tianhy.springbootjms.controller;

import com.tianhy.springbootjms.pojo.Result;
import com.tianhy.springbootjms.pojo.User;
import com.tianhy.springbootjms.service.ActiveMqService;
import com.tianhy.springbootjms.service.ActiveMqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
@RequestMapping("/activemq")
@Controller
public class ActiveMqController {

    @Autowired
    private ActiveMqService activeMqService = null;

    @Autowired
    private ActiveMqUserService activeMqUserService = null;



    //发送消息
    @RequestMapping("/msg")
    @ResponseBody
    public Result msg(@NotNull String msg) {
        activeMqService.sendMsg(msg);
        return Result.success().add("message", msg);
    }

    //发送对象
    @RequestMapping("/user")
    @ResponseBody
    public Result msg(Long id,String userName,String note) {
        User user = new User(id,userName,note);
        activeMqUserService.sendUser(user);
        return Result.success().add("message", user);
    }

}
