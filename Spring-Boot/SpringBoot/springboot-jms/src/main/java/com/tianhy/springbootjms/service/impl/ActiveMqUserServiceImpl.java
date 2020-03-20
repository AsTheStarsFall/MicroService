package com.tianhy.springbootjms.service.impl;

import com.tianhy.springbootjms.pojo.User;
import com.tianhy.springbootjms.service.ActiveMqUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


/**
 * {@link}
 *
 * @Desc: 对象的发送/接收，ActiveMQ不信任User，需要在配置文件设置trust-all
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
@Service
public class ActiveMqUserServiceImpl implements ActiveMqUserService {

    @Autowired
    private JmsTemplate jmsTemplate = null;

    private static final String myDestination = "my-destination";

    @Override
    public void sendUser(User user) {
        System.out.println("ActiveMQ发送User对象 【" + user + "】");
        jmsTemplate.convertAndSend(myDestination, user);
    }

    @Override
    @JmsListener(destination = myDestination)
    public void receiveUser(User user) {
        System.out.println("ActiveMQ接收User对象 【" + user + "】");
    }
}
