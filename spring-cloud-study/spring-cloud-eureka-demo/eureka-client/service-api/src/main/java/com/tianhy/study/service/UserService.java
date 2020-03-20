package com.tianhy.study.service;

import com.tianhy.study.domain.User;

import java.util.Collection;


/**
 * @Author: thy
 * @Date: 2020/2/19 5:44
 * @Desc:
 */
public interface UserService {

    /**
     * 保存
     *
     * @param user
     * @return
     */
    boolean save(User user);

    /**
     * 查询所有用户
     *
     * @return
     */
    Collection<User> findAll();

}
