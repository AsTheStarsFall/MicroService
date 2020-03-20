package com.tianhy.study.controller;


import com.tianhy.study.service.IHelloService;
import com.tianhy.study.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
public class HelloController {

    @Autowired
    private IHelloService helloService;

    @GetMapping("/getProviderData")
    public List<String> getProviderData() {
        return helloService.getProviderData();
    }

    /**
     * @return
     */
    @RequestMapping(value = "/helloService", method = RequestMethod.GET)
    public String getHelloServiceData() {
        return "hello Service";
    }
}
