package com.tianhy.springbootc4aop.aspect.service;

import com.tianhy.springbootc4aop.jdbc.pojo.User;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
public interface UserService {
    public void printUser(User user);

    public void manyAspects();
}
