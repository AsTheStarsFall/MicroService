package com.tianhy.springbootc4aop.aspect.service.impl;

import com.tianhy.springbootc4aop.aspect.service.UserService;
import com.tianhy.springbootc4aop.jdbc.pojo.User;
import org.springframework.stereotype.Service;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void printUser(User user) {
        if(user == null){
            throw new RuntimeException("user is null");
        }
        System.out.println(user.toString());
    }

    @Override
    public void manyAspects() {
        System.out.println("多切面测试...");
    }
}
