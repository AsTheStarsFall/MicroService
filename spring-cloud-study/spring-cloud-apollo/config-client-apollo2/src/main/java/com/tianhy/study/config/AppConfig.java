package com.tianhy.study.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: thy
 * @Date: 2020/2/18 1:59
 * @Desc:
 */
@Configuration
@EnableApolloConfig(value = "TEST1.zuul-config", order = 10)
public class AppConfig {
}
