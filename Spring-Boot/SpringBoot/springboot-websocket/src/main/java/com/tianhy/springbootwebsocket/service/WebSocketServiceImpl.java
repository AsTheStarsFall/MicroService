package com.tianhy.springbootwebsocket.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link}
 *
 * @Desc: websocket服务端点， /ws
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
@Slf4j
//让spring创建websocket的服务端点，请求地址是"/ws"
@ServerEndpoint(("/ws"))
@Service
public class WebSocketServiceImpl {

    //记录当前在线连接数
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    //存储每个客户端对应的WebSocketServiceImpl对象
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<>();
    //与某个客户端的连接会话，需要用Session给客户端发送消息
    private Session session;

    //连接建立成功后调用的方法
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("当前用户ID ：" + this.session.getId());
        //存储一个客户端对应的WebSocketServiceImpl
        webSocketSet.add(this);
        //连接数加1
        addOnlineCount();
        System.out.println("有新连接加入，当前在线人数：" + onlineCount.get());
        try {
            sendMessage("有新的连接加入");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("IO异常");
        }
    }

    /**
     * 发送消息
     *
     * @param message 客户端消息
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 收到客户端消息调用的方法
     *
     * @param message 客户端发来的消息
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息 : " + message);
        //群发消息
        for (WebSocketServiceImpl websocket : webSocketSet) {
            try {
                //当前用户名称
//                String name = session.getUserPrincipal().getName();
//                System.out.println("当前用户：" + name);
//                System.out.println("当前用户ID ：" + this.session.getId());
                //群发
                websocket.sendMessage(message);
            } catch (IOException e) {
            }
        }
    }

    /**
     * 发送错误调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
//        log.error("错误消息来自：" + session.getUserPrincipal().getName());
        error.printStackTrace();
    }

    //连接关闭后调用
    @OnClose
    public void onClose() {
        //从webSocketSet移除
        System.out.println("关闭连接");
        webSocketSet.remove(this);
        //在线数减1
        subOnlineCount();
    }

    //在线数减1
    private void subOnlineCount() {
        onlineCount.getAndDecrement();
    }

    //连接数加1
    private void addOnlineCount() {
        onlineCount.getAndIncrement();
    }
}
