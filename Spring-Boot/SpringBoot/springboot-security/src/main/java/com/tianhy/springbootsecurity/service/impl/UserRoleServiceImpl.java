package com.tianhy.springbootsecurity.service.impl;

import com.tianhy.springbootsecurity.dao.RoleDao;
import com.tianhy.springbootsecurity.dao.UserDao;
import com.tianhy.springbootsecurity.pojo.DataBaseRole;
import com.tianhy.springbootsecurity.pojo.DataBaseUser;
import com.tianhy.springbootsecurity.service.UserRoleService;
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
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    @Override
    public DataBaseUser getUserByName(String user_name) {
        return userDao.getUser(user_name);
    }

    @Override
    public List<DataBaseRole> findRolesByUserName(String userName) {
        return roleDao.findRolesByUserName(userName);
    }
}
