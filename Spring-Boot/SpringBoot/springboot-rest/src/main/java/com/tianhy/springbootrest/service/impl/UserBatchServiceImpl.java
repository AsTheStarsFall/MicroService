package com.tianhy.springbootrest.service.impl;

import com.tianhy.springbootrest.pojo.User;
import com.tianhy.springbootrest.service.UserBatchService;
import com.tianhy.springbootrest.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/17
 **/
@Service
public class UserBatchServiceImpl implements UserBatchService, ApplicationContextAware {

    @Autowired
    private UserService userService =null;


    //使用代理对象解决自调用失效问题
    private ApplicationContext applicationContext = null;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int insertUsers(List<User> list) {
        //从IOC容器中获取代理对象
        UserService userService1 = applicationContext.getBean(UserService.class);
        int count = 0;
        for (User user : list) {
            //调用子方法
//            count+=userService.insertUser(user);

            //调用自身类的方法，会失效
//            count+=insertUser(user);

            //使用代理对象
            count+=userService1.insertUser(user);

        }
        return count;
    }

    //自调用失效
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1,propagation = Propagation.REQUIRES_NEW)
    public int insertUser(User user){
        return userService.insertUser(user);
    }

    //实现生命周期的方法，设置IOC
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
    }
}
