package com.tianhy.springbootjms.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
@Service
@Slf4j
public class ScheduleServiceImpl {

    int count = 1;
    int count1 = 1;

    @Scheduled(fixedRate = 1000)
    @Async
    public void jod() {
        System.out.println("[" + Thread.currentThread().getName() + "]\n" +
                "[job] 每秒执行一次，执行第" + count + "次"
        );
        ++count;
    }

    @Scheduled(fixedRate = 1000)
    @Async
    public void jod1() {
        System.out.println("[" + Thread.currentThread().getName() + "]\n" +
                "[job] 每秒执行一次，执行第" + count1 + "次"
        );
        ++count1;
    }
}
