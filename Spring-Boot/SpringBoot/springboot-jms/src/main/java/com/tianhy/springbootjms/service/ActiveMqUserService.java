package com.tianhy.springbootjms.service;

import com.tianhy.springbootjms.pojo.User;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
public interface ActiveMqUserService {
    public void sendUser(User user);

    public void receiveUser(User user);
}
