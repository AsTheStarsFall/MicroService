package com.tianhy.springbootsecurity.service;

import com.tianhy.springbootsecurity.pojo.DataBaseRole;
import com.tianhy.springbootsecurity.pojo.DataBaseUser;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
public interface UserRoleService {

    public DataBaseUser getUserByName(String user_name);

    public List<DataBaseRole> findRolesByUserName(String userName);

}
