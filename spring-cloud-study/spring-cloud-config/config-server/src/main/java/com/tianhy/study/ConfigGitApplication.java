package com.tianhy.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author: thy
 * @Date: 2020/2/15 2:18
 * @Desc: 配置启动类
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigGitApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigGitApplication.class, args);
    }

}
