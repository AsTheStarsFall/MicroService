package com.tianhy.springbootmongodb.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.tianhy.springbootmongodb.pojo.User;
import com.tianhy.springbootmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mgtl = null;

    @Override
    public void saveUser(User user) {
        // 使用名称为user文档保存用户信息
        mgtl.save(user, "user");
        // 如果文档采用类名首字符小写，则可以这样保存
        // mongoTmpl.save(user);
    }

    @Override
    public DeleteResult deleteUser(Long id) {
        Criteria criteria = Criteria.where("id").is(id);
        Query query = Query.query(criteria);
        DeleteResult result = mgtl.remove(query, User.class);
        return result;
    }

    @Override
    public List<User> findUser(String userName, String note, int skip, int limit) {
        Criteria regex = Criteria.where("userName").regex(userName).and("note").regex(note);
        //构建查询条件
        Query query = Query.query(regex).limit(limit).skip(skip);
        //执行
        List<User> userList = mgtl.find(query, User.class);
        return userList;
    }

    @Override
    public UpdateResult updateUser(Long id, String userName, String note) {
        Criteria criteriaId = Criteria.where("id").is(id);
        Query query = Query.query(criteriaId);
        Update update = Update.update("user_name", userName);
        update.set("note", note);
        //更新单个对象
        UpdateResult updateResult = mgtl.updateFirst(query, update, User.class);
        //更新多个对象
//        mgtl.updateMulti();
        return updateResult;
    }

    @Override
    public User getUser(Long id) {
        return mgtl.findById(id, User.class);
    }

    @Override
    public List<User> findAllUsers() {
        return mgtl.findAll(User.class);
    }
}
