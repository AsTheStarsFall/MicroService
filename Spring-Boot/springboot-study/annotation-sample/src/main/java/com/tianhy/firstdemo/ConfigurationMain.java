package com.tianhy.firstdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/2 12:05
 **/
public class ConfigurationMain {
    public static void main(String[] args) {

        //传入Java Configuration 配置类，在配置类中将bean注入了
        AnnotationConfigApplicationContext acac =
                new AnnotationConfigApplicationContext(ConfigurationDemo.class);

        //从IOC获取bean
        DemoClass bean = acac.getBean(DemoClass.class);
        bean.say();
//        String[] beanDefinitionNames = acac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
    }
}
