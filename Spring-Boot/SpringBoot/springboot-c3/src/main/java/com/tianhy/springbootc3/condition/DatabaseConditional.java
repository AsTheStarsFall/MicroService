package com.tianhy.springbootc3.condition;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
public class DatabaseConditional implements Condition {
    /**
     *
     * 数据库装配条件
     * @param context 条件上下文
     * @param metadata 元数据
     * @return 返回
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return env.containsProperty("jdbc.driverName") && env.containsProperty("jdbc.url")
                && env.containsProperty("jdbc.username") && env.containsProperty("jdbc.password");
    }
}
