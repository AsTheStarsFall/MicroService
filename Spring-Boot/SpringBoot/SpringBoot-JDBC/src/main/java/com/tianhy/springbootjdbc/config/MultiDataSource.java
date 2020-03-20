package com.tianhy.springbootjdbc.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * {@link}
 *
 * @Desc: 多数据源配置
 * @Author: thy
 * @CreateTime: 2019/4/11
 **/
@Configuration
public class MultiDataSource {

    /**
    * @Description: 主数据源
    * @return: javax.sql.DataSource
    * @Author: thy
    * @Date: 2019/4/11
    */
    @Bean
    @Primary
    public DataSource masterDataSource(){
        DataSourceBuilder builder = DataSourceBuilder.create();
        DataSource dataSource = builder
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/test")
                .username("root")
                .password("root")
                .build();
        return dataSource;
    }


    @Bean
    public DataSource slaveDataSource(){
        DataSourceBuilder builder = DataSourceBuilder.create();
        DataSource dataSource = builder
                .driverClassName("com.mysql.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/test2")
                .username("root")
                .password("root")
                .build();
        return dataSource;
    }
}
