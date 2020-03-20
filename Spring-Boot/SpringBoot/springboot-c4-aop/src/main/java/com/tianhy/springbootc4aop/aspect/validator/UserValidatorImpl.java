package com.tianhy.springbootc4aop.aspect.validator;

import com.tianhy.springbootc4aop.jdbc.pojo.User;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
public class UserValidatorImpl implements UserVolidator {
    @Override
    public boolean volidate(User user) {
        System.out.println("引入新接口 : "+ UserVolidator.class.getSimpleName()+" 校验user是否为空");
        return user != null;
    }
}
