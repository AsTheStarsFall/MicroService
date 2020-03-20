package com.tianhy.springcloudeurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//启用Feign
@EnableFeignClients(basePackages = "com.tianhy.springcloudeurekaclient")
//启用断路器Hystrix
@EnableCircuitBreaker
/**
 * @SpringCloudApplication其中包含：
 * @SpringBootApplication
 * @EnableDiscoveryClient
 * @EnableCircuitBreaker
 */
//@SpringCloudApplication
//@EnableDiscoveryClient
public class SpringcloudEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaClientApplication.class, args);
    }

    //初始化RestTemplate
    //多节点负载均衡
    @LoadBalanced
    @Bean(name = "restTemplate")
    public RestTemplate initRestTemplate() {
        return new RestTemplate();
    }
}
