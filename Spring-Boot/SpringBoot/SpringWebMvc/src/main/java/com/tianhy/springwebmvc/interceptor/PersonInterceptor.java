package com.tianhy.springwebmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Desc: 拦截器
 * @Author: thy
 * @CreateTime: 2019/4/9
 **/
public class PersonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //具体的请求方法
        System.out.printf("handler object %s \n",handler.toString());
        return true;
    }
}
