package com.tianhy.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: thy
 * @Date: 2020/2/19 15:31
 * @Desc:
 */
@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private EurekaClientConfigBean eurekaClientConfigBean;

    @GetMapping("/eureka-server")
    public Map<String, String> getEurekaServerUrl() {
        return eurekaClientConfigBean.getServiceUrl();
    }


}
