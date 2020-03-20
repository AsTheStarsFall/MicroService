package com.tianhy.springbootc4aop.service.impl;

import com.tianhy.springbootc4aop.service.HelloService;
import org.apache.commons.lang3.StringUtils;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        if(StringUtils.isBlank(name)){
            throw new RuntimeException("name is null");
        }
        System.out.println("你好");
    }
}
