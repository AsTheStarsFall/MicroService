package com.tianhy.study.service.impl;

import com.tianhy.study.service.IUserService;
import org.springframework.stereotype.Component;

/**
 * @Author: thy
 * @Date: 2020/2/23 17:42
 * @Desc:
 */
@Component
public class UserServiceFallback implements IUserService {
    @Override
    public String getUser(String username) {
        return "The user does not exist in this system, please confirm username";
    }
}
