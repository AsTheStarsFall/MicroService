package com.tianhy.firstdemo;

import org.springframework.context.annotation.*;

/**
 * {@link}
 *
 * @Desc: 通过Java Configuration 的方式注入
 * @Author: thy
 * @CreateTime: 2019/10/2 12:03
 **/
@Configuration
public class ConfigurationDemo {

    //返回值作为bean定义注册到spring IOC容器中,方法名默认为bean id
    @Bean
    public DemoClass demoClass(){
        return new DemoClass();
    }


}
