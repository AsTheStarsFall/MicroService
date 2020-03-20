package com.tianhy.springbootc4aop.aspect.controller;

import com.tianhy.springbootc4aop.aspect.service.UserService;
import com.tianhy.springbootc4aop.aspect.service.impl.UserServiceImpl;
import com.tianhy.springbootc4aop.aspect.validator.UserVolidator;
import com.tianhy.springbootc4aop.jdbc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService =null;


    @RequestMapping("/print")
    @ResponseBody
    public User printUser(int id,String userName,String password,int age){
        User user = new User();
        user.setId(id);
        user.setUser_name(userName);
        user.setPassword(password);
        user.setAge(age);

        UserVolidator userVolidator = (UserVolidator) userService;
        if(userVolidator.volidate(user)){
            userService.printUser(user);
        }
        return user;
    }

    @RequestMapping("/manyAspects")
    @ResponseBody
    public String manyAspects(){
        userService.manyAspects();
        return "manyAspects";
    }
}
