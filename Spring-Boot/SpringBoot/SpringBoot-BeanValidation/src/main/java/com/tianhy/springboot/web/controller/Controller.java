package com.tianhy.springboot.web.controller;

import com.tianhy.springboot.domain.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/4/13
 **/
@RestController
public class Controller {

    @PostMapping("/user/save")
    public User save(@Valid @RequestBody User user){

        return user;
    }
}
