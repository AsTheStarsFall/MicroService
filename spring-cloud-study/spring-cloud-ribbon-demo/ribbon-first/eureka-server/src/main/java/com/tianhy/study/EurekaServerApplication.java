package com.tianhy.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.http.EurekaApplications;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author: thy
 * @Date: 2020/2/23 2:03
 * @Desc:
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class,args);
    }
}
