package com.tianhy.springbootmongodb.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.tianhy.springbootmongodb.pojo.User;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
public interface UserService {
    public void saveUser(User user);

    public DeleteResult deleteUser(Long id);

    public List<User> findUser(String userName, String note, int skip, int limit);

    public UpdateResult updateUser(Long id, String userName, String note);

    public User getUser(Long id);

    public List<User> findAllUsers();
}
