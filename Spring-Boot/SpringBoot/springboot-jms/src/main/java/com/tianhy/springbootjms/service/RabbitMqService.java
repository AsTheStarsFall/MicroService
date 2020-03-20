package com.tianhy.springbootjms.service;

import com.tianhy.springbootjms.pojo.User;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
public interface RabbitMqService {
    //发送消息
    public void sendMsg(String msg);

    public void sendUser(User user);

}
