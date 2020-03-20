package com.tianhy.thirddemo.current;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/2 13:13
 **/
public class ThirdMain {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(ThirdSpringConfig.class);
        String[] beanDefinitionNames = acac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

    }
}
