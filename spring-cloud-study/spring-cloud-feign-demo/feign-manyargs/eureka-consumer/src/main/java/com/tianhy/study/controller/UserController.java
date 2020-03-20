package com.tianhy.study.controller;

import com.tianhy.study.domain.User;
import com.tianhy.study.service.UserFeignService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: thy
 * @Date: 2020/2/22 15:20
 * @Desc:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFeignService userFeignService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@RequestBody @ApiParam(name = "用户", value = "json格式", required = true) User user) {
        return userFeignService.addUser(user);
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser( @RequestBody @ApiParam(name="用户",value="传入json格式",required=true) User user){
        return userFeignService.updateUser(user);
    }


}
