package com.tianhy.springbootc3.service;

import com.tianhy.springbootc3.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
//服务类
@Service
@Slf4j
public class UserService {

    public void printUser(User user){
        log.info("编号：{}",user.getId());
        log.info("用户名称：{}",user.getUserName());
        log.info("备注：{}",user.getNote());
    }
}
