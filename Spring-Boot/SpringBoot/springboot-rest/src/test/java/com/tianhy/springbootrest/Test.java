package com.tianhy.springbootrest;


import com.tianhy.springbootrest.enumeration.SexEnum;
import com.tianhy.springbootrest.pojo.User;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/20
 **/
public class Test {

    public static void main(String[] args) {
        User user = new User(1L,"userName","note",SexEnum.FEMALE);
    }
}
