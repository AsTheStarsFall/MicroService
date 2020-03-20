package com.tianhy.springbootc5jdbc.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * {@link}
 *
 * @Desc: 检测数据库连接池类型
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@Slf4j
@Component
//ApplicationContextAware spring bean生命周期接口
public class DataSourceShow implements ApplicationContextAware {

    ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        log.info("-------------------------------");
        log.info(dataSource.getClass().getName());

        log.info("-------------------------------");

        //org.apache.commons.dbcp2.BasicDataSource

    }
}
