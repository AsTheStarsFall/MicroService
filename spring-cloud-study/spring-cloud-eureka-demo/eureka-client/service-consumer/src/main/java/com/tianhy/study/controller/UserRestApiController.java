package com.tianhy.study.controller;

import com.tianhy.study.domain.User;
import com.tianhy.study.service.UserServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @Author: thy
 * @Date: 2020/2/19 6:20
 * @Desc:
 */
@RestController
public class UserRestApiController {

    @Autowired
    private UserServiceProxy proxy;

    @PostMapping("/user/save")
    public User save(@RequestBody User user) {

        if (proxy.save(user)) {
            System.out.println("保存成功:" + user);
            return user;
        } else {
            return null;
        }
    }

    @GetMapping("/user/list")
    public Collection<User> findAll() {
        return proxy.findAll();
    }

}
