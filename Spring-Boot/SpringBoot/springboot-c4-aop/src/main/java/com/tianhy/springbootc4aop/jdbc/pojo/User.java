package com.tianhy.springbootc4aop.jdbc.pojo;

import lombok.Data;

/**
 * {@link}
 *
 * @Desc: 用户POJO
 * @Author: thy
 * @CreateTime: 2019/4/11
 **/
@Data
public class User {

    private int id;
    private String user_name;
    private int age;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}
