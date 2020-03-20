package com.tianhy.seconddemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/2 12:05
 **/
//默认扫描当前包下
@ComponentScan(basePackages = "com.tianhy.seconddemo")
public class ConfigurationMain {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext acac =
                new AnnotationConfigApplicationContext(ConfigurationMain.class);

        DemoClass bean = acac.getBean(DemoClass.class);
        bean.say();
//        String[] beanDefinitionNames = acac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
    }
}
