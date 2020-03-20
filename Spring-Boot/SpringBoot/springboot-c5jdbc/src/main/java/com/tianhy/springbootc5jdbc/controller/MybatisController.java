package com.tianhy.springbootc5jdbc.controller;

import com.tianhy.springbootc5jdbc.pojo.User;
import com.tianhy.springbootc5jdbc.service.MybatisUserService;
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
@RequestMapping("/mybatis")
@Controller
public class MybatisController {

    @Autowired
    private MybatisUserService userService = null;


    @RequestMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
