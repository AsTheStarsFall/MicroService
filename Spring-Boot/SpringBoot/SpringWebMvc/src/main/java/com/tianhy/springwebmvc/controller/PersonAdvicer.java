package com.tianhy.springwebmvc.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc: 异常处理
 * @Author: thy
 * @CreateTime: 2019/4/10
 **/
@RestControllerAdvice(basePackages = "com.tianhy.springboot.springwebmvc.controller")
public class PersonAdvicer {

    @ExceptionHandler(value = {NullPointerException.class, IllegalAccessException.class, IllegalStateException.class})
    public Object handlerNpe(Throwable throwable) {
        Map<String, Object> errs = new HashMap<>();
        errs.put("errMessage", throwable.getMessage());
        return errs;
    }
}
