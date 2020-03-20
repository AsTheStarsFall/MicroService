package com.tianhy.springbootmongodb.controller;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.tianhy.springbootmongodb.pojo.Result;
import com.tianhy.springbootmongodb.pojo.User;
import com.tianhy.springbootmongodb.repository.UserRepository;
import com.tianhy.springbootmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService = null;

    @Autowired
    private UserRepository userRepository = null;

    @RequestMapping("/page")
    public String page() {
        return "user";
    }

    /**
     * 保存（新增或者更新）用户
     *
     * @param user -- 用户
     * @return 用户信息
     */
    @RequestMapping("/save")
    @ResponseBody
    public User saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    /***
     * 获取用户
     * @param id -- 用户主键
     * @return 用户信息
     */
    @RequestMapping("/get")
    @ResponseBody
    public User getUser(Long id) {
        User user = userService.getUser(id);
        return user;
    }


    /**
     * 查询用户
     *
     * @param userName --用户名称
     * @param note     -- 备注
     * @param skip     -- 跳过用户个数
     * @param limit    -- 限制返回用户个数
     * @return
     */
    @RequestMapping("/find")
    @ResponseBody
    public List<User> findUser(String userName, String note, Integer skip, Integer limit) {
        List<User> userList = userService.findUser(userName, note, skip, limit);
        return userList;
    }

    /**
     * 更新用户部分属性
     *
     * @param id       —— 用户编号
     * @param userName —— 用户名称
     * @param note     —— 备注
     * @return 更新结果
     */
    @RequestMapping("/update")
    @ResponseBody
    public UpdateResult updateUser(Long id, String userName, String note) {
        return userService.updateUser(id, userName, note);
    }

    /**
     * 删除用户
     *
     * @param id -- 用户主键
     * @return 删除结果
     */
    @RequestMapping("/delete")
    @ResponseBody
    public DeleteResult deleteUser(Long id) {
        return userService.deleteUser(id);
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Result findAllUsers() {
        List<User> allUsers = userService.findAllUsers();
        return Result.success().add("info", allUsers);
    }

    @RequestMapping("/likeName/{name}")
    @ResponseBody
    public Result findByUserNameLike(@PathVariable String name) {
        List<User> users = userRepository.findByUserNameLike(name);
        return Result.success().add("info", users);
    }

}
