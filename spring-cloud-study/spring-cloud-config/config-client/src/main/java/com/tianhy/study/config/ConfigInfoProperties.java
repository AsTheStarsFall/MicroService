package com.tianhy.study.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: thy
 * @Date: 2020/2/16 1:19
 * @Desc: 用于注入远程上的配置信息
 */
@Component
@ConfigurationProperties(prefix = "sc.spring.cloud")
public class ConfigInfoProperties {

    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
