package com.tianhy.springbootredis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * {@link}
 *
 * @Desc: RedisMessageListener 消息监听器
 * @Author: thy
 * @CreateTime: 2019/5/17
 **/
@Component
@Slf4j
public class RedisMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //消息体
        String msgBody = new String(message.getBody());
        //渠道名称
        String channel = new String(pattern);
        log.info("消息体 {}", msgBody);
        log.info("渠道 {}", channel);
    }
}
