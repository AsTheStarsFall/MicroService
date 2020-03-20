package com.tianhy.springbootmybatis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

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
//    PlatformTransactionManager platformTransactionManager = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        PlatformTransactionManager platformTransactionManager = applicationContext.getBean(PlatformTransactionManager.class);

        log.info("--------数据源类型--------");
        log.info(dataSource.getClass().getName());
        log.info("--------------------------");
        log.info("--------事务管理器--------");
        log.info(platformTransactionManager.getClass().getName());
        log.info("--------------------------");

    }
}
