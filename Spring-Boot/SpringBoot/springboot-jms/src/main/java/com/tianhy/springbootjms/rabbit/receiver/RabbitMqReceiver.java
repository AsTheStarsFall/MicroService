package com.tianhy.springbootjms.rabbit.receiver;

import com.tianhy.springbootjms.pojo.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
@Component
public class RabbitMqReceiver {

    //定义监听字符串队列名称
    @RabbitListener(queues = {"${rabbitmq.queue.msg}"})
    public void receiveMsg(String msg) {
        System.out.println("RabbitMQ接收 message :" + msg);
    }

    //定义监听User队列名称
    @RabbitListener(queues = {"${rabbitmq.queue.user}"})
    public void receiveUser(User user) {
        System.out.println("RabbitMQ接收 user :" + user);
    }
}
