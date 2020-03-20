package com.tianhy.springbootrest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(
        basePackages = "com.tianhy.springbootrest.mapper",
        annotationClass = Repository.class
)
public class SpringbootRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRestApplication.class, args);
    }

}
