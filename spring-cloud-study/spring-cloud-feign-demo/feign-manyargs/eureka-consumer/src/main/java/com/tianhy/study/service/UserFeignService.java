package com.tianhy.study.service;

import com.tianhy.study.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: thy
 * @Date: 2020/2/22 15:21
 * @Desc:
 */
@FeignClient(name = "feign-provider")
public interface UserFeignService {

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String addUser(User user);

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String updateUser(@RequestBody User user);


}
