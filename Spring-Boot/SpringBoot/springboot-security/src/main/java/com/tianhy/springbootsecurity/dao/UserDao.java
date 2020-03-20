package com.tianhy.springbootsecurity.dao;

import com.tianhy.springbootsecurity.pojo.DataBaseUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
@Mapper
@Repository
public interface UserDao {
    public DataBaseUser getUser(String user_name);
}
