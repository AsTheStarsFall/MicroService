package com.tianhy.study.impl;

import com.netflix.discovery.converters.Auto;
import com.tianhy.study.domain.User;
import com.tianhy.study.repository.UserRepository;
import com.tianhy.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @Author: thy
 * @Date: 2020/2/19 5:56
 * @Desc:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }
}
