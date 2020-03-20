package com.tianhy.springbootjms.service.impl;

import com.tianhy.springbootjms.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
@Service
public class ActiveMqServiceImpl implements ActiveMqService {

    //注入springboot自动生产的JmsTemplate
    @Autowired
    private JmsTemplate jmsTemplate = null;


    @Override
    public void sendMsg(String msg) {
        System.out.println("ActiveMQ发送消息【" + msg + "】");
        jmsTemplate.convertAndSend(msg);
        //自定义发送地址
//        jmsTemplate.convertAndSend("destination",msg);
    }


    @Override
    //监听地址发送过来的消息
    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void receiveMsg(String msg) {
        System.out.println("ActiveMQ接收消息【" + msg + "】");
    }
}
