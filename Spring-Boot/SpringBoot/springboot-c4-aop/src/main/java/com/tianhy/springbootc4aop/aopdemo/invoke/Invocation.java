package com.tianhy.springbootc4aop.aopdemo.invoke;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Data
public class Invocation {

    private Object[] params;

    private Method method;

    private Object target;

    public Invocation(Object[] params, Method method, Object target) {
        this.params = params;
        this.method = method;
        this.target = target;
    }

    //以反射的形式，调用原有的方法
    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target,params);
    }
}
