package com.tianhy.springbootc5jdbc.dao;

import com.tianhy.springbootc5jdbc.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@Repository
public interface MybatisUserDao {
    public User getUser(Long id);
}
