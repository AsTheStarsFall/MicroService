package com.tianhy.springbootjms.controller;

import com.tianhy.springbootjms.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
@RequestMapping("/async")
@Controller
public class AsyncController {

    @Autowired
    private AsyncService service = null;

    @GetMapping("/page")
    @ResponseBody
    public String asyncPage() {
        System.out.println("请求线程名称：" + "[" + Thread.currentThread().getName() + "]");
        //调用异步服务
        service.generateReport();
        return "async";
    }
}
