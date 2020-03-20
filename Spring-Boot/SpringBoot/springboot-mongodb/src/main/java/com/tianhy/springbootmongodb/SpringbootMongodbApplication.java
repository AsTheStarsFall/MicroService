package com.tianhy.springbootmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//指定Mongo扫描的包，用于扫描继承了MongoRepository的接口
@EnableMongoRepositories(basePackages = "com.tianhy.springbootmongodb.repository")
public class SpringbootMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMongodbApplication.class, args);
    }

}
