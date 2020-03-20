package com.tianhy.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: thy
 * @Date: 2020/2/23 17:46
 * @Desc:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderServiceApplication.class,args);
    }
}
