package com.tianhy.springbootwebsocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
@RequestMapping("/websocket")
@Controller
public class WebSocketController {


    //注入springboot自动配置的模板
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    //跳转页面
    @GetMapping("/index")
    public String websocket(ModelMap modelMap) {
        return "websocket";
    }


    //发送页面
    @GetMapping("/send")
    public String send() {
        return "send";
    }

    // 接收页面
    @GetMapping("/receive")
    public String receive() {
        return "receive";
    }


    // 对特定用户发送页面
    @GetMapping("/sendUser")
    public String sendUser() {
        return "send-user";
    }

    // 接收用户消息页面
    @GetMapping("/receiveUser")
    public String receiveUser() {
        return "receive-user";
    }


    //发送到特定路径
    @MessageMapping("/send")
    @SendTo("/sub/chat")
    public String sendMsg(String value) {
        System.out.println("/sub/chat " + value);
        return value;
    }

    //将消息发送特定的用户
    @MessageMapping("/sendUser")
    public void sendToUser(Principal principal, String body) {
        String principalName = principal.getName();
        //解析用户和消息
        String[] args = body.split(",");
        String desUser = args[0];
        String message = "【" + principalName + "】给你发来消息：" + args[1];
        //发送到用户和监听地址
        simpMessagingTemplate.convertAndSendToUser(desUser, "/queue/customer", message);
    }
}
