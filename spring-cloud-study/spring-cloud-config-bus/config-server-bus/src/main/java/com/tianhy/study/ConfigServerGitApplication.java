package com.tianhy.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author: thy
 * @Date: 2020/2/16 15:20
 * @Desc:
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerGitApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerGitApplication.class, args);
    }

}
