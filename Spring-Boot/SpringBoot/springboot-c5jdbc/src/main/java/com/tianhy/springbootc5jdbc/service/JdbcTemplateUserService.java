package com.tianhy.springbootc5jdbc.service;

import com.tianhy.springbootc5jdbc.pojo.User;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
public interface JdbcTemplateUserService {

    public User getUserById(Long id);

    public List<User> findUsers(String userName, String note);

    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUser(Long id);

    public User getUser1(Long id);

    public User getUser2(Long id);


}
