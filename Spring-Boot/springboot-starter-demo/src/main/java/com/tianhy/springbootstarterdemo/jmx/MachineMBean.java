package com.tianhy.springbootstarterdemo.jmx;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/3 15:52
 **/
public interface MachineMBean {

    int getCpuCore();

    long getFreeMemory();

    void shutdown();
}
