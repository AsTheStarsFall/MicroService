package com.tianhy.springclouddashbord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
//启用Hystrix仪表盘
@EnableHystrixDashboard
public class SpringcloudDashbordApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudDashbordApplication.class, args);
    }

}
