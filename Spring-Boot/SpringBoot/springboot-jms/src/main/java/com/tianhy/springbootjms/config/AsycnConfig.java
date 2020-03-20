package com.tianhy.springbootjms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * {@link}
 *
 * @Desc: 定义线程池
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
@Configuration
//开启异步
@EnableAsync
public class AsycnConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        //定义线程池
        ThreadPoolTaskExecutor task = new ThreadPoolTaskExecutor();
        //核心线程数
        task.setCorePoolSize(10);
        //最大线程数
        task.setMaxPoolSize(30);
        //线程队列最大线程数
        task.setQueueCapacity(2000);
        //初始化
        task.initialize();
        return task;
    }
}
