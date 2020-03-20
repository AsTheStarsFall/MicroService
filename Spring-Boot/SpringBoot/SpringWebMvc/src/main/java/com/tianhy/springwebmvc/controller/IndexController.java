package com.tianhy.springwebmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/4/10
 **/
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("message","tianhy");
        return "index";
    }
}
