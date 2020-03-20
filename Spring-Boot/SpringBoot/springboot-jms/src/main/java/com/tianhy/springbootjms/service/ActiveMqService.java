package com.tianhy.springbootjms.service;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
public interface ActiveMqService {
    //发送消息
    public void sendMsg(String msg);

    //接收消息
    public void receiveMsg(String msg);
}
