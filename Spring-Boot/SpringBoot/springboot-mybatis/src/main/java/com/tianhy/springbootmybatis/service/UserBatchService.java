package com.tianhy.springbootmybatis.service;

import com.tianhy.springbootmybatis.pojo.User;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/17
 **/
public interface UserBatchService {

    public int insertUsers(List<User> list);
}
