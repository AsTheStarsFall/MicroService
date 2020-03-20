package com.tianhy.springbootwebsocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
//使用STOMP协议：简单(流)文本定向消息协议，它提供了一个可互操作的连接格式
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//    //创建服务端点
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }

    //注册服务端点
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        //添加一个服务端点
        registry.addEndpoint("/socket").withSockJS();
        //添加一个用户服务端点
        registry.addEndpoint("/wsuser").withSockJS();
    }

    //定义服务端点请求和订阅前缀
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //客户端订阅前缀
        registry.enableSimpleBroker("/sub","/queue");
        //服务端点请求前缀
        registry.setApplicationDestinationPrefixes("/request");
    }
}
