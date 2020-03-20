package com.tianhy.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: thy
 * @Date: 2020/2/23 19:17
 * @Desc:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
public class DashboarHelloServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DashboarHelloServiceApplication.class, args);
    }
}
