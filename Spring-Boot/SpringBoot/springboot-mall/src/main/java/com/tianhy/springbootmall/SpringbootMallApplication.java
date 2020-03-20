package com.tianhy.springbootmall;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(
        basePackages = "com.tianhy.springbootmall",
        annotationClass = Mapper.class
)
//定时任务
@EnableScheduling
public class SpringbootMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMallApplication.class, args);
    }

}
