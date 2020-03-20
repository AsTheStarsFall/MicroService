package com.tianhy.springbootspringmvc.service.impl;

import com.tianhy.springbootspringmvc.dao.UserDao;
import com.tianhy.springbootspringmvc.pojo.User;
import com.tianhy.springbootspringmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> findUsers(String userName, String note) {
        return userDao.findUsers(userName,note);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }
}
