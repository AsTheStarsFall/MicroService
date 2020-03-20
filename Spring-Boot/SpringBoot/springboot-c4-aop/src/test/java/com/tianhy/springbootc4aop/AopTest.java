package com.tianhy.springbootc4aop;

import com.tianhy.springbootc4aop.aopdemo.Interceptor.MyInterceptor;
import com.tianhy.springbootc4aop.aopdemo.proxy.ProxyBean;
import com.tianhy.springbootc4aop.service.HelloService;
import com.tianhy.springbootc4aop.service.impl.HelloServiceImpl;


/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
public class AopTest {

    public static void main(String[] args) {
        //目标对象
        HelloService helloService = new HelloServiceImpl();
        HelloService proxy = (HelloService)ProxyBean.getProxyBean(helloService,new MyInterceptor());
        proxy.sayHello("你好");
        System.out.println("name is null...");
        proxy.sayHello(null);
    }
}
