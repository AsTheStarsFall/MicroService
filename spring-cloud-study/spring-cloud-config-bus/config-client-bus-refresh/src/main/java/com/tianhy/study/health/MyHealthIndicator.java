package com.tianhy.study.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * @Author: thy
 * @Date: 2020/2/18 23:53
 * @Desc: 健康检查
 */
public class MyHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("MyHealthIndicator", "up");
    }
}
