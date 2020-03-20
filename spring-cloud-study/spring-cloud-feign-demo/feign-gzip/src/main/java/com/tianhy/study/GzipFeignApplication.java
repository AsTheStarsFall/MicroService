package com.tianhy.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: thy
 * @Date: 2020/2/22 3:42
 * @Desc:
 */
@SpringBootApplication
@EnableFeignClients
public class GzipFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(GzipFeignApplication.class,args);
    }
}
