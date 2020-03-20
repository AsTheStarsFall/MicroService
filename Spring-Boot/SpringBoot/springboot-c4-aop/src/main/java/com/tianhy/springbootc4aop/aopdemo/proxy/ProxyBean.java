package com.tianhy.springbootc4aop.aopdemo.proxy;

import com.tianhy.springbootc4aop.aopdemo.Interceptor.Interceptor;
import com.tianhy.springbootc4aop.aopdemo.invoke.Invocation;

import java.lang.reflect.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
public class ProxyBean implements InvocationHandler {

    private Object target = null;
    private Interceptor interceptor = null;

    /**
     *
     * @param target 被代理对象
     * @param interceptor 拦截器
     * @return 代理对象
     */
    public static Object getProxyBean(Object target, Interceptor interceptor){
        ProxyBean proxyBean = new ProxyBean();
        proxyBean.target = target;
        proxyBean.interceptor = interceptor;
        Object proxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), proxyBean);
        return proxyInstance;
    }

    /**
     * 处理代理对象方法逻辑
     * @param proxy 代理对象
     * @param method 当前方法
     * @param args 运行参数
     * @return 方法调用结果
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        boolean exFlag = false;
        //根据代理对象构建 Invocation,其中proceed()方法调用原有代理对象的方法
        Invocation invocation = new Invocation(args,method,target);
        Object retObj = null;
        try {
            //按约定，从before开始
            if(this.interceptor.before()){
                //执行around()方法
                retObj = this.interceptor.around(invocation);
            }else {
                retObj = method.invoke(target,args);
            }
        }catch (Exception e){
            exFlag = true;
        }
        interceptor.after();
        if(exFlag){
            interceptor.afterThrowing();
        }else {
            interceptor.afterReturing();
            return retObj;
        }
        return null;
    }
}
