package com.tianhy.study.repository;

import com.tianhy.study.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: thy
 * @Date: 2020/2/19 5:52
 * @Desc:
 */
@Repository
public class UserRepository {

    private ConcurrentMap<Long, User> userConcurrentMap = new ConcurrentHashMap<>();

    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    public Collection<User> findAll() {
        return userConcurrentMap.values();
    }

    public boolean save(User user) {
        long id = idGenerator.incrementAndGet();
        user.setId(id);
        return userConcurrentMap.putIfAbsent(id, user) == null;
    }


}
