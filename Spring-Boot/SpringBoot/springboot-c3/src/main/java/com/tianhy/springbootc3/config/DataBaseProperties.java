package com.tianhy.springbootc3.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * {@link}
 *
 * @Desc: application.properties 属性配置
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Slf4j
@Component
//使用@ConfigurationProperties 配置属性
@ConfigurationProperties("jdbc")
public class DataBaseProperties {

//    @Value("${jdbc.driverName}")
    private String driverName = null;

//    @Value("${jdbc.url}")
    private String url = null;

    private String username = null;

    private String password = null;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        log.info("载入jdbc.driverName [{}]",driverName);
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        log.info("载入 jdbc.url [{}]",url);
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

//    @Value("${jdbc.username}")
    public void setUsername(String username) {
        log.info("载入 jdbc.username [{}]",username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

//    @Value("${jdbc.password}")
    public void setPassword(String password) {
        log.info("载入 jdbc.password [{}]",password);
        this.password = password;
    }
}
