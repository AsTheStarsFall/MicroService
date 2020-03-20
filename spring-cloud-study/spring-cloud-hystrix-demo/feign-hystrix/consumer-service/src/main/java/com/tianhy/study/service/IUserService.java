package com.tianhy.study.service;

import com.tianhy.study.service.impl.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: thy
 * @Date: 2020/2/23 17:20
 * @Desc:
 */
//指向sc-provider-service服务，并设置fallback类
@FeignClient(name = "provider-service", fallback = UserServiceFallback.class)
public interface IUserService {

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUser(@RequestParam("username") String username);


}
