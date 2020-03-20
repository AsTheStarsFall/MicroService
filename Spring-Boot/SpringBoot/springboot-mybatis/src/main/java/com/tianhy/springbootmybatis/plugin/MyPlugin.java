package com.tianhy.springbootmybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@Intercepts({
        @Signature(type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class})
})
public class MyPlugin implements Interceptor {

    Properties properties = null;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
//        System.out.println("------拦截器方法------");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
//        this.properties = properties;
//        System.out.println("------获取properties属性------");
//        System.out.println(this.properties.get("key1"));
    }
}
