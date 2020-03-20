package com.tianhy.springbootsecurity.controller;

import com.tianhy.springbootsecurity.pojo.DataBaseUser;
import com.tianhy.springbootsecurity.pojo.Thymeleaf;
import com.tianhy.springbootsecurity.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
@Controller
public class IndexController {
    @Autowired
    private UserRoleService userRoleService = null;

    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/user/details")
    @ResponseBody
    public DataBaseUser getUser(String userName) {
        return userRoleService.getUserByName(userName);
    }

    @GetMapping("/user/welcome")
    public String userWelcome() {
        return "index";
    }

    @GetMapping("/admin/welcome")
    public String adminWelcome() {
        return "index";
    }

    @GetMapping("/admin/welcome1")
    public String adminWelcome1() {
        return "index";
    }

    @GetMapping("/admin/welcome2")
    public String adminWelcome2() {
        return "index";
    }

    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }

//    @GetMapping("/csrf/form")
//    public String csrfPage() {
//        return "csrf_form";
//    }

    @GetMapping("/csrf/form")
    public String thymeleaf(Model model) {
        model.addAttribute("thymeleaf", new Thymeleaf());
        return "csrf_form";
    }

    @PostMapping("/csrf/commit")
    @ResponseBody
    public Map<String, String> csrfCommit(@ModelAttribute Thymeleaf thymeleaf) {
        Map<String, String> map = new HashMap<>();
        map.put("name", thymeleaf.getName());
        map.put("describe", thymeleaf.getDescribe());
        return map;
    }
}
