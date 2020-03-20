package com.tianhy.springbootc4aop.aopdemo.Interceptor;

import com.tianhy.springbootc4aop.aopdemo.invoke.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * {@link}
 *
 * @Desc: 根据Interceptor接口的定义，实现自定义拦截器
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
public class MyInterceptor implements Interceptor {
    @Override
    public boolean before() {
        System.out.println("before...");
        return true;
    }

    @Override
    public void after() {
        System.out.println("after...");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {

        System.out.println("around before...");
        Object proceed = invocation.proceed();
        System.out.println("around after...");
        return proceed;
    }

    @Override
    public void afterReturing() {
        System.out.println("afterReturing...");

    }

    @Override
    public void afterThrowing() {
        System.out.println("afterThrowing...");

    }

    @Override
    public boolean useAround() {
        return true;
    }
}
