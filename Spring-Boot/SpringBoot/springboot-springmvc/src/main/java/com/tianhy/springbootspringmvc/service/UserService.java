package com.tianhy.springbootspringmvc.service;

import com.tianhy.springbootspringmvc.pojo.User;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
public interface UserService {
    public User getUser(Long id);
    List<User> findUsers(String userName, String note);
    int insertUser(User user);

}
