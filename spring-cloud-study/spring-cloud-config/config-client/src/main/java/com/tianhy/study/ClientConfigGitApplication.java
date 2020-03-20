package com.tianhy.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: thy
 * @Date: 2020/2/16 1:17
 * @Desc: 客户端的作用是向服务端拉取配置
 */
@SpringBootApplication
public class ClientConfigGitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientConfigGitApplication.class, args);
    }

}
