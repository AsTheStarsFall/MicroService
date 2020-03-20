package com.tianhy.springbootspringmvc.controller.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/22
 **/
@ControllerAdvice(
        basePackages = "com.tianhy.springbootspringmvc.controller.advice.test.*",
        annotations = Controller.class
)
public class MyControllerAdvice {

    //绑定格式化，参数转换规则，增加验证器
    public void initDataBinder(WebDataBinder binder) {
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    //在执行器之前执行，初始化数据模型
    @ModelAttribute
    public void initModel(Model model) {
        model.addAttribute("initModel", "tianhy");
    }

    //异常处理
    @ExceptionHandler(value = Exception.class)
    public String exception(Model model, Exception ex) {
        model.addAttribute("exception", ex.getMessage());
        return "exception";
    }
}
