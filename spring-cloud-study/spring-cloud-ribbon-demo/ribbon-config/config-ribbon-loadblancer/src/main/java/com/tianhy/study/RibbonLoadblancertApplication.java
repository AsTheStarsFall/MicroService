package com.tianhy.study;

import com.tianhy.study.config.AnnotationConfig;
import com.tianhy.study.config.AvoidScan;
import com.tianhy.study.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: thy
 * @Date: 2020/2/23 2:12
 * @Desc:
 */
@SpringBootApplication
@EnableDiscoveryClient
//对源服务进行负载均衡
@RibbonClient(name = "config-eureka-client", configuration = AnnotationConfig.class)
@ComponentScan(excludeFilters =
        {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {AvoidScan.class})})
/*
@RibbonClients(value = {
        @RibbonClient(name = "client-a", configuration = RibbonConfig.class),
        @RibbonClient(name = "client-b", configuration = RibbonConfig.class)
})
*/
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
