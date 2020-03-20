package com.tianhy.springbootjms.service.impl;

import com.tianhy.springbootjms.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
@Service
public class AsyncServiceImpl implements AsyncService {


    @Override
    //声明使用异步调用
    @Async
    public void generateReport() {
        System.out.println("调用线程名称:" + "[" + Thread.currentThread().getName() + "]");
    }
}
