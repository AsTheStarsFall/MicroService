package com.tianhy.study.controller;

import com.tianhy.study.config.ConfigInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: thy
 * @Date: 2020/2/16 1:25
 * @Desc:
 */
@RefreshScope
@RestController
public class ConfigClientController {

    @Autowired
    private ConfigInfoProperties configInfoProperties;

    @GetMapping("/getConfigInfo")
    public String getConfigInfo() {
        return configInfoProperties.getConfig();
    }
}
