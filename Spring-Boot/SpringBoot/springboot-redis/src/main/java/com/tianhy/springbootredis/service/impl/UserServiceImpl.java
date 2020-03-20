package com.tianhy.springbootredis.service.impl;

import com.tianhy.springbootredis.mapper.UserMapper;
import com.tianhy.springbootredis.pojo.User;
import com.tianhy.springbootredis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.util.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/18
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper = null;

    @Override
    @Transactional
    //移除缓存
    @CacheEvict(value = "redisCache", key = "'redis_user_'+#id", beforeInvocation = false)
    public int deleteUser(Long id) {
        return userMapper.deleteUser(id);
    }

    @Override
    @Transactional
    //更新后更新缓存，如果条件配置 结果为null 不缓存
    @CachePut(value = "redisCache",
            condition = "#result != 'null'",
            key = "'redis_user_'+#result.id")
    public User updateUser(User user) {
        userMapper.updateUser(user);
        //调用getUser方法 该方法缓存失效
        //还会执行sql
        User get = getUser(user.getId());
        if (get == null) {
            return null;
        }
        return get;
    }

    @Override
    @Transactional
    //获取ID  取参数ID缓存用户
    @Cacheable(value = "redisCache", key = "'redis_user_'+#id")
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
    @Transactional
    public List<User> findUsers(User user) {
        return userMapper.findUsers(user.getUserName(), user.getNote());
    }

    @Override
    @Transactional
    //插入用户，最后Mybatis会回填ID，取结果ID缓存用户
    @CachePut(value = "redisCache", key = "'redis_user_'+#result.id")
    public User insertUser(User user) {
//        log.info(Json.toJson(user));
        userMapper.insertUser(user);
        return user;
    }
}
