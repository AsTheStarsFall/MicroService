package com.tianhy.study;

import com.tianhy.study.health.MyHealthIndicator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

/**
 * @Author: thy
 * @Date: 2020/2/16 15:41
 * @Desc:
 */
@SpringBootApplication
public class ConfigClientGitApplication {
    private final ContextRefresher contextRefresher;

    private final Environment environment;

    public ConfigClientGitApplication(ContextRefresher contextRefresher, Environment environment) {
        this.contextRefresher = contextRefresher;
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientGitApplication.class,args);
    }

    //定时刷新
    @Scheduled(fixedRate = 5 * 1000, initialDelay = 3 * 1000)
    public void autoRefresh() {
        Set<String> updatedPropertyNames = contextRefresher.refresh();
        updatedPropertyNames.forEach( propertyName ->
                System.err.printf("[Thread :%s] 当前配置已更新，具体 Key：%s , Value : %s \n",
                        Thread.currentThread().getName(),
                        propertyName,
                        environment.getProperty(propertyName)
                ));
    }

    //将健康检查注入
    @Bean
    public MyHealthIndicator myHealthIndicator(){
        return new MyHealthIndicator();
    }
}
