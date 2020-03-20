package com.tianhy.springbootredis.service;

import com.tianhy.springbootredis.pojo.User;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/18
 **/
public interface UserService {

    public User getUser(Long id);

    public User insertUser(User user);

    public int deleteUser(Long id);

    public User updateUser(User user);

    public int batchInsert(List<User> list);

    public List<User> selectBySex(int sex);

    public List<User> selectAll(RowBounds rowBounds);

    public List<User> selectListLimit(int start, int pageSize);

    public List<User> findUsers(User user);

}
