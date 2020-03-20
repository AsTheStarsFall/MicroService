package com.tianhy.springbootc5jdbc.service.impl;

import com.tianhy.springbootc5jdbc.dao.MybatisUserDao;
import com.tianhy.springbootc5jdbc.pojo.User;
import com.tianhy.springbootc5jdbc.service.MybatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@Service
public class MybatisUserServiceImpl implements MybatisUserService {

    @Autowired
    private MybatisUserDao mybatisUserDao = null;

    @Override
    public User getUser(Long id) {
        return mybatisUserDao.getUser(id);
    }
}
