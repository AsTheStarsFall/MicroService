package com.tianhy.springbootmybatis.service.impl;

import com.tianhy.springbootmybatis.mapper.UserMapper;
import com.tianhy.springbootmybatis.pojo.User;
import com.tianhy.springbootmybatis.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper = null;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 1)
    public User getUser(Long id) {
        return userMapper.getUser(id);
    }

    @Override
    public int batchInsert(List<User> list) {
        return userMapper.batchInsert(list);
    }

    @Override
    public List<User> selectBySex(int sex) {
        return userMapper.selectBySex(sex);
    }

    @Override
    public List<User> selectAll(RowBounds rowBounds) {
        return userMapper.selectAll(rowBounds);
    }

    @Override
    public List<User> selectListLimit(int start, int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("start", (start - 1) * pageSize);
        map.put("pageSize", pageSize);
        return userMapper.selectListLimit(map);
    }

    @Override
    //Propagation.REQUIRES_NEW 新建事务
    //Propagation.NESTED 如果子方法发生异常，只回滚子方法的事务
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1,propagation = Propagation.NESTED)
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        userMapper.updateUser(user);
        return user;
    }
}
