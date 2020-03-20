package com.tianhy.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * {@link}
 *
 * @Desc: 用户注册
 * @Author: thy
 * @CreateTime: 2020/3/5 1:02
 **/
@Service
public class UserRegisitry {

    //用户注册的时候，对密码进行加密处理
    public int register(String userName, String pwd) {
        //密码编码器
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        //密码加密，返回加密后的密码字符串
        String encode = passwordEncoder.encode(pwd);
        return saveToDb(userName, encode);
    }

    private int saveToDb(String userName, String encode) {
        return 0;
    }


}
