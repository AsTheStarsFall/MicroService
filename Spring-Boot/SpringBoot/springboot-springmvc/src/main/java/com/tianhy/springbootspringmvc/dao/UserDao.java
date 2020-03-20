package com.tianhy.springbootspringmvc.dao;

import com.tianhy.springbootspringmvc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public User getUser(Long id);
    List<User> findUsers(@Param("userName") String userName, @Param("note") String note);
    int insertUser(User user);
}
