package com.tianhy.springbootc4aop.aspect;

import com.tianhy.springbootc4aop.aspect.validator.UserValidatorImpl;
import com.tianhy.springbootc4aop.aspect.validator.UserVolidator;
import com.tianhy.springbootc4aop.jdbc.pojo.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

/**
 * {@link}
 *
 * @Desc: 定义切面
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
@Aspect
@Order(1)
public class MyAspect {

    //定义切点
    @Pointcut("execution(* com.tianhy.springbootc4aop.aspect.service.impl.UserServiceImpl.printUser(..))")
//    @Pointcut(value = "execution(* com.tianhy.springbootc4aop.*.*.*.*.printUser(..)) && bean('userServiceImpl')")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before() {
        System.out.println("before...");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after...");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning...");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing...");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("around before...");
        jp.proceed();
        System.out.println("around after...");
    }


    /**
     * @DeclareParents 引入新的类来增强服务
     * value 指向要增强的目标对象，此处增强的是UserServiceImpl
     * defaultImpl 引入增强功能类，UserValidatorImpl 提供校验user是否为空的功能
     */
    @DeclareParents(value = "com.tianhy.springbootc4aop.aspect.service.impl.UserServiceImpl",defaultImpl = UserValidatorImpl.class)
    public UserVolidator userVolidator;

    @Before("pointCut()&& args(user)")
    public void beforeParam(JoinPoint point, User user){
        //连接点方法参数
        Object[] args = point.getArgs();
        System.out.println(Arrays.toString(args));
    }
}
