package com.tianhy.springbootspringmvc.controller.advice.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import org.apache.tools.ant.util.DateUtils;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/22
 **/
@RequestMapping("/advice")
@Controller
public class Test {


    @GetMapping("/test")
    public String test(Date date, ModelMap modelMap){
        //获取初始化数据模型的时候传入的值
        System.out.println(modelMap.get("initModel"));
        System.out.println(DateUtils.format(date,"yyyy-MM-dd"));
        throw new RuntimeException("异常");
    }

}
