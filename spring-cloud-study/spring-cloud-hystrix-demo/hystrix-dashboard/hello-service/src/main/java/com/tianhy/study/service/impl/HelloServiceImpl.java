package com.tianhy.study.service.impl;

import com.tianhy.study.service.IHelloService;
import com.tianhy.study.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: thy
 * @Date: 2020/2/23 19:36
 * @Desc:
 */
@Component
public class HelloServiceImpl implements IHelloService {

    @Autowired
    private IProviderService providerService;

    @Override
    public List<String> getProviderData() {
        return providerService.getProviderData();
    }
}
