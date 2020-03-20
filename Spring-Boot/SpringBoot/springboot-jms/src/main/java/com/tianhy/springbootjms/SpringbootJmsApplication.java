package com.tianhy.springbootjms;


import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//开启定时任务
@EnableScheduling
public class SpringbootJmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJmsApplication.class, args);
    }

    //消息队列名称
    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName = null;

    //用户队列名称
    @Value("${rabbitmq.queue.user}")
    private String userQueueName= null;

    //创建消息队列
    @Bean
    public Queue createQueueMsg(){
        return new Queue(msgQueueName,true);
    }

    @Bean
    public Queue createQueueUser(){
        return new Queue(userQueueName,true);
    }
}
