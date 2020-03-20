package com.tianhy.springbootspringmvc.controller;

import com.tianhy.springbootspringmvc.pojo.User;
import com.tianhy.springbootspringmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/22
 **/
@RequestMapping("/session")
@Controller
//指定数据模型名称或属性名称，保存到Session中
//@SessionAttributes(names = {"user"},types = Long.class)
public class SessionController {

    @Autowired
    private UserService userService;

    @GetMapping("/page")
    public String sessionPage(){
        return "session";
    }

    @GetMapping("/test")
    public String test(Model model){
        //根据类型保存到session
        model.addAttribute("id",1);
        User user = userService.getUser(1L);
        model.addAttribute("user",user);
        return "session/test";
    }
}
