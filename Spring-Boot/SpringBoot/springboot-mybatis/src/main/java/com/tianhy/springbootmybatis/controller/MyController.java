package com.tianhy.springbootmybatis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianhy.springbootmybatis.enumeration.SexEnum;
import com.tianhy.springbootmybatis.pojo.Result;
import com.tianhy.springbootmybatis.pojo.User;
import com.tianhy.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@RestController
public class MyController {

    @Autowired
    private UserService userService = null;


    @RequestMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @RequestMapping("/users/{page}")
    @ResponseBody
    public List<User> getUsers(@PathVariable Integer page) {
        PageHelper.startPage(page, 10);
        return null;
    }

    //PageHelper 分页插件
    @RequestMapping("/user/sex/{sex}/{pn}")
    @ResponseBody
    public Result getUserBySex(@PathVariable Integer sex, @PathVariable Integer pn) {
        PageHelper.startPage(pn, 10);
        List<User> selectBySex = userService.selectBySex(sex);
        PageInfo pageInfo = new PageInfo(selectBySex, 10);
        return Result.success().add("pageInfo", pageInfo);
    }

    @RequestMapping("/user/update")
    @ResponseBody
    public User updateUser(@RequestBody User user) {
//        User user = new User(id,userName,note, SexEnum.getSexEnumById(sex));
        return userService.updateUser(user);
    }

    @RequestMapping("/user/insert")
    @ResponseBody
    public int insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }
}
