package com.tianhy.springbootmall.controller;

import com.tianhy.springbootmall.pojo.Result;
import com.tianhy.springbootmall.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/27
 **/
@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/test")
    public ModelAndView testPage() {
        ModelAndView mv = new ModelAndView("test");
        return mv;
    }

    @PostMapping("/purchase")
    public Result purchase(Long userId, Long productId, Integer quantity) {
//        boolean flag = purchaseService.purchase(userId, productId, quantity);

        //Redis处理
        boolean flag = purchaseService.purchaseRedis(userId, productId, quantity);
        return flag == true ? Result.success() : Result.fail();
    }

}
