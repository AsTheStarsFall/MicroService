package com.tianhy.study.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author: thy
 * @Date: 2020/2/23 19:34
 * @Desc:
 */
@FeignClient(value = "sc-provider-service")
public interface IProviderService {

    @RequestMapping(value = "/getDashboard", method = RequestMethod.GET)
    public List<String> getProviderData();

}
