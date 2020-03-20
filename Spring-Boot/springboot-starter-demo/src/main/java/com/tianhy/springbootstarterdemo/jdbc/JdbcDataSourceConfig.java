package com.tianhy.springbootstarterdemo.jdbc;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/3 13:29
 **/
@Configuration
public class JdbcDataSourceConfig {


    // 1、读取配置文件
    @Primary
    @Bean
    //通过这个注解读取appliaction.properties配置中的数据源配置信息
    @ConfigurationProperties(prefix = "app.datasource.db1")
    public DataSourceProperties db1dataSourceProperties(){
        return new DataSourceProperties();
    }

    //通过这个注解读取appliaction.properties配置中的数据源配置信息
    @Bean
    @ConfigurationProperties(prefix = "app.datasource.db2")
    public DataSourceProperties db2dataSourceProperties(){
        return new DataSourceProperties();
    }

    //2、创建数据源
    @Primary
    @Bean
    public DataSource db1dataSource(){
        return db1dataSourceProperties().initializeDataSourceBuilder().build();
    }
    @Bean
    public DataSource db2dataSource(){
        return db2dataSourceProperties().initializeDataSourceBuilder().build();
    }

    //3、创建JdbcTemplate
    @Bean("db1JdbcTemplate")
    public JdbcTemplate db1JdbcTemplate(){
        return new JdbcTemplate(db1dataSource());
    }

    @Bean("db2JdbcTemplate")
    public JdbcTemplate db2JdbcTemplate(){
        return new JdbcTemplate(db2dataSource());
    }


}
