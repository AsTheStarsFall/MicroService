package com.tianhy.springbootspringmvc.controller;

import com.tianhy.springbootspringmvc.pojo.User;
import com.tianhy.springbootspringmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * {@link}
 *
 * @Desc: 数据模型
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
@RequestMapping("/data")
@Controller
public class DataModelController {

    @Autowired
    private UserService userService;

    @RequestMapping("/model/{id}")
    public String userModel(@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "data/user";
    }


    //字符串指定跳转
    @GetMapping("/redirect1")
    public String redirect1(String userName, String note) {
        User user = new User();
        user.setUserName(userName);
        user.setNote(note);
        //插入数据库后
        userService.insertUser(user);
        return "redirect:/data/model/" + user.getId();
    }

    //使用ModelAndView指定跳转
    @GetMapping("/redirect2")
    public ModelAndView redirect2(String userName, String note) {
        User user = new User();
        user.setNote(note);
        user.setUserName(userName);
        userService.insertUser(user);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/data/model/" + user.getId());
        return mv;
    }


    //重定向传递Java对象
    @RequestMapping("/showUser")
    public String showUser(User user, Model model) {
        System.out.println(user.getId());
        return "data/user";
    }

    // 使用字符串指定跳转
    @RequestMapping("/redirect3")
    public String redirect1(String userName, String note, RedirectAttributes ra) {
        User user = new User();
        user.setNote(note);
        user.setUserName(userName);
        userService.insertUser(user);
        // 保存需要传递给重定向的对象
        ra.addFlashAttribute("user", user);
        return "redirect:/data/showUser";
    }

    // 使用模型和视图指定跳转
    @RequestMapping("/redirect2")
    public ModelAndView redirect2(String userName, String note,
                                  RedirectAttributes ra) {
        User user = new User();
        user.setNote(note);
        user.setUserName(userName);
        userService.insertUser(user);
        // 保存需要传递给重定向的对象
        ra.addFlashAttribute("user", user);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/data/showUser");
        return mv;
    }

}
