package com.tianhy.springbootc5jdbc.controller;

import com.tianhy.springbootc5jdbc.dao.JpaUserRepository;
import com.tianhy.springbootc5jdbc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@Controller
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    private JpaUserRepository userRepository = null;

    @RequestMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @RequestMapping("/findUsers")
    @ResponseBody
    public List<User> findUsers(String userName, String note) {
        List<User> userList = userRepository.findUsers(userName, note);
        return userList;
    }

    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(Long id) {
        // 使用JPA接口查询对象
        User user = userRepository.getUserById(id);
        return user;
    }

    @RequestMapping("/findByUserNameLike")
    @ResponseBody
    public List<User> findByUserNameLike(String userName) {
        // 使用JPA接口查询对象
        List<User> userList = userRepository.findByUserNameLike("%" + userName + "%");
        return userList;
    }

    @RequestMapping("/findByUserNameLikeOrNoteLike")
    @ResponseBody
    public List<User> findByUserNameLikeOrNoteLike(String userName, String note) {
        String userNameLike = "%" + userName + "%";
        String noteLike = "%" + note + "%";
        // 使用JPA接口查询对象
        List<User> userList = userRepository.findByUserNameLikeOrNoteLike(userNameLike, noteLike);
        return userList;
    }

}
