package com.tianhy.fourthdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * {@link}
 *
 * @Desc: 动态注入
 * @Author: thy
 * @CreateTime: 2019/10/2 13:53
 **/
@SpringBootApplication
@EnableDefinitionService
public class EnableDemoMain {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(EnableDemoMain.class, args);
        System.out.println(context.getBean(AService.class));
        System.out.println(context.getBean(BService.class));
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
    }
}
