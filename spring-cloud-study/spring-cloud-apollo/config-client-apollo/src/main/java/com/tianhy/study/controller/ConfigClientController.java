package com.tianhy.study.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config_info}")
    private String config;

    @GetMapping("/getConfigInfo")
    public String getConfigInfo() {
        return config;
    }
}
