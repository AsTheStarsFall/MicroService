package com.tianhy.study.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Author: thy
 * @Date: 2020/2/16 1:19
 * @Desc: 用于注入远程上的配置信息
 */
@Component
//延迟加载，只有第一次访问时才会被初始化
// 刷新Bean也是同理，下次访问会创建一个新的对象
@RefreshScope
public class ConfigInfoProperties {

    @Value("${sc.spring.cloud.config}")
    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
