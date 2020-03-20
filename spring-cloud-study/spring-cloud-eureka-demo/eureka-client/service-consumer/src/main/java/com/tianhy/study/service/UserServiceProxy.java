package com.tianhy.study.service;

import com.tianhy.study.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * @Author: thy
 * @Date: 2020/2/19 6:21
 * @Desc: 服务消费者的代理，使用RestTemplate访问服务生产者
 */
@Service
public class UserServiceProxy implements UserService {

    private static final String PROVIDER_SERVICE_URL_PREFIX = "http://service-provider";

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public boolean save(User user) {

        User postForObject = restTemplate.postForObject(PROVIDER_SERVICE_URL_PREFIX + "/user/save", user, User.class);

        return postForObject != null;
    }

    @Override
    public Collection<User> findAll() {
        return restTemplate.getForObject(PROVIDER_SERVICE_URL_PREFIX + "/user/list", Collection.class);
    }
}
