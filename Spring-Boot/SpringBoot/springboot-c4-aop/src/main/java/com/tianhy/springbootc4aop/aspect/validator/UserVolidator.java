package com.tianhy.springbootc4aop.aspect.validator;

import com.tianhy.springbootc4aop.jdbc.pojo.User;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
public interface UserVolidator {

    public boolean volidate(User user);
}
