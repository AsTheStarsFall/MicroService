package com.tianhy.fourthdemo;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

/**
 * {@link}
 *
 * @Desc: 实现ImportSelector接口进行动态注入
 * @Author: thy
 * @CreateTime: 2019/10/2 13:48
 **/
public class AImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //获取注解元素信息
        MultiValueMap<String, Object> allAnnotationAttributes =
                importingClassMetadata.getAllAnnotationAttributes(EnableDefinitionService.class.getName());

        //根据注解元素属性，动态的注入
        for (String s : allAnnotationAttributes.keySet()) {

        }

        //返回类的全限定名,会被纳入IOC容器中
        //注入Aservice
        return new String[]{AService.class.getName()};
    }
}
