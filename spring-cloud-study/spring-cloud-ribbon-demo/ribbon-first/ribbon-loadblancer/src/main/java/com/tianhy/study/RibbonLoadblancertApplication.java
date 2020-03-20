package com.tianhy.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: thy
 * @Date: 2020/2/23 2:12
 * @Desc:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RibbonLoadblancertApplication {
    public static void main(String[] args) {
        SpringApplication.run(RibbonLoadblancertApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
