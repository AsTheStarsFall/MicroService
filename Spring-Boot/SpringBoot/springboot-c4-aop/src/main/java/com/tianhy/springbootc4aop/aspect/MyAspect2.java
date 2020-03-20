package com.tianhy.springbootc4aop.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Aspect
@Order(3)
public class MyAspect2 {
    //切点
    @Pointcut("execution(* com.tianhy.springbootc4aop.aspect.service.impl.UserServiceImpl.manyAspects(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before() {
        System.out.println("MyAspect2 before...");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("MyAspect2 after...");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("MyAspect2 afterReturning...");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("MyAspect2 afterThrowing...");
    }
}
