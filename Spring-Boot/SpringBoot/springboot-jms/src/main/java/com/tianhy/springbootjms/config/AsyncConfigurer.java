package com.tianhy.springbootjms.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.lang.Nullable;

import java.util.concurrent.Executor;

/**
 * {@link}
 *
 * @Desc: 定义配置异步线程池接口
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
public interface AsyncConfigurer {

    //获取线程池
    @Nullable
    default Executor getAsyncExecutor() {
        return null;
    }

    //异步异常处理
    @Nullable
    default AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

}
