package com.tianhy.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 */
@SpringBootApplication
@EnableEurekaServer
public class FeignServiceEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignServiceEurekaServerApplication.class, args);
    }
}