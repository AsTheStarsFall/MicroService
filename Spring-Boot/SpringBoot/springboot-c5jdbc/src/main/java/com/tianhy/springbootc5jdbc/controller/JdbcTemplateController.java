package com.tianhy.springbootc5jdbc.controller;

import com.tianhy.springbootc5jdbc.pojo.User;
import com.tianhy.springbootc5jdbc.service.JdbcTemplateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@Controller
@RequestMapping("/jdbc")
public class JdbcTemplateController {

    @Autowired
    private JdbcTemplateUserService userService  = null;


    @RequestMapping("/user/{id}")
    @ResponseBody
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @RequestMapping("/user/save")
    @ResponseBody
    public int insertUser(@RequestBody User user){
        System.out.println("------/user/save------");
        System.out.println(user.toString());
        return userService.insertUser(user);
    }


    @RequestMapping("/user1/{id}")
    @ResponseBody
    public User getUser1(@PathVariable Long id){
        System.out.println("------/user1------");
        return userService.getUser1(id);
    }

    @RequestMapping("/user2/{id}")
    @ResponseBody
    public User getUser2(@PathVariable Long id){
        System.out.println("------/user2------");
        return userService.getUser2(id);
    }
}
