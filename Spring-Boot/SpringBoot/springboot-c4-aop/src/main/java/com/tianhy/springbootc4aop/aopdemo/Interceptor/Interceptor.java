package com.tianhy.springbootc4aop.aopdemo.Interceptor;


import com.tianhy.springbootc4aop.aopdemo.invoke.Invocation;

import java.lang.reflect.InvocationTargetException;

/**
 * {@link}
 *
 * @Desc: 拦截器接口
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
public interface Interceptor {

    //事前
    public boolean before();

    //事后
    public void after();

    /**
     * 取代原有事件方法
     * @param invocation 回调参数，可以通过它的proceed()方法，回调原事件
     * @return 原有事件返回对象
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object around(Invocation invocation)throws InvocationTargetException,IllegalAccessException;

    //事后放回方法，事件没有发生异常执行
    public void afterReturing();

    //事后异常方法，事件发生异常执行
    public void afterThrowing();

    //是否使用around()方法取代原有方法
    boolean useAround();

}
