package com.tianhy.springbootredis.controller;

import com.tianhy.springbootredis.enumeration.SexEnum;
import com.tianhy.springbootredis.pojo.Result;
import com.tianhy.springbootredis.pojo.User;
import com.tianhy.springbootredis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/18
 **/
@RequestMapping("/cache")
@Controller
@Slf4j
public class CacheController {

    @Autowired
    private UserService service = null;


    @RequestMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

    @RequestMapping("/user/insert")
    @ResponseBody
    public User insertUser(@RequestParam String userName,@RequestParam String note,@RequestParam Integer sex) {
        User user = new User(null,userName,note,sex == 1? SexEnum.FEMALE : SexEnum.MALE);
//        log.info(Json.toJson(user));
        return service.insertUser(user);
    }

    @RequestMapping("/user/findUsers")
    @ResponseBody
    public List<User> findUsers(User user) {
        return service.findUsers(user);
    }

    @RequestMapping("/user/update")
    @ResponseBody
    public User updateUser(User user) {
        return service.insertUser(user);
    }

    @RequestMapping("/user/delete/{id}")
    @ResponseBody
    public Result deleteUser(@PathVariable Long id) {
        int result = service.deleteUser(id);
        boolean flag = result == 1;
        return flag == true ? Result.success() : Result.fail();
    }

}
