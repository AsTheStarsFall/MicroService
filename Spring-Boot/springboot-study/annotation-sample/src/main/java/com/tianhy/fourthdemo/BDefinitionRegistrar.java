package com.tianhy.fourthdemo;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

/**
 * {@link}
 *
 * @Desc: 实现ImportBeanDefinitionRegistrar接口进行动态注入
 * @Author: thy
 * @CreateTime: 2019/10/2 13:50
 **/
public class BDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        Class clazz = BService.class;
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(BService.class);
        //首字母小写
        String beanName = StringUtils.uncapitalize(clazz.getName());
        //注入Bservice
        registry.registerBeanDefinition(beanName, rootBeanDefinition);
    }
}
