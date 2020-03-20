package com.tianhy.study.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: thy
 * @Date: 2020/2/23 3:15
 * @Desc: 基于注解的策略设置
 */
@Configuration
@AvoidScan
public class AnnotationConfig {

    @Autowired
    private IClientConfig iClientConfig;

    public IRule ribbonRule(IClientConfig iClientConfig) {
        return new RandomRule();
    }
}
