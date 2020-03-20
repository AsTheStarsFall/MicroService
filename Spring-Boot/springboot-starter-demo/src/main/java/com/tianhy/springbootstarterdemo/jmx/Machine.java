package com.tianhy.springbootstarterdemo.jmx;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/3 15:52
 **/
public class Machine implements MachineMBean {
    @Override
    public int getCpuCore() {

        return Runtime.getRuntime().availableProcessors();
    }

    @Override
    public long getFreeMemory() {

        return Runtime.getRuntime().freeMemory();

    }

    @Override
    public void shutdown() {
        System.exit(0);
    }
}
