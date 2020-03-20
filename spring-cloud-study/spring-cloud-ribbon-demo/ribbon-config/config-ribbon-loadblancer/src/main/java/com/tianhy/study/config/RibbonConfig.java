package com.tianhy.study.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: thy
 * @Date: 2020/2/23 3:12
 * @Desc: 全局策略配置
 */
//@Configuration
public class RibbonConfig {
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
