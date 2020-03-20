package com.tianhy.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * {@link}
 *
 * @Desc: 全局异常处理
 * @Author: thy
 * @CreateTime: 2020/3/6 21:11
 **/
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AuthorizationException.class)
    public ModelAndView error(AuthorizationException e) {
        ModelAndView modelAndView = new ModelAndView("unauthorized");
        modelAndView.addObject("error", e.getMessage());
        return modelAndView;
    }
}
