package com.tianhy.springbootjms.service.impl;

import com.tianhy.springbootjms.pojo.User;
import com.tianhy.springbootjms.service.RabbitMqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
@Service
public class RabbitMqServiceImpl implements RabbitMqService, RabbitTemplate.ConfirmCallback {

    //消息队列名称
    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName = null;

    //用户队列名称
    @Value("${rabbitmq.queue.user}")
    private String userQueueName = null;

    @Autowired
    private RabbitTemplate rabbitTemplate = null;

    @Override
    public void sendMsg(String msg) {
        System.out.println("RabbitMQ发送消息【" + msg + "】");
        //设置回调
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(msgQueueName, msg);
    }

    @Override
    public void sendUser(User user) {
        System.out.println("RabbitMQ发送User【" + user + "】");
        //设置回调
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(msgQueueName,user);
    }

    //回调确认
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if(b){
            System.out.println("消息消费成功");
        }else {
            System.out.println("消息消费失败 ："+ s);
        }
    }
}
