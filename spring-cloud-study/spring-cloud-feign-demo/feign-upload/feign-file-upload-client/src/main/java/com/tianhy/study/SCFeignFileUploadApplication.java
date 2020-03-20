package com.tianhy.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: thy
 * @Date: 2020/2/22 17:21
 * @Desc:
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class SCFeignFileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SCFeignFileUploadApplication.class,args);
    }
}
