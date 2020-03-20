package com.tianhy.springbootmongodb.repository.impl;

import com.tianhy.springbootmongodb.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


/**
 * {@link}
 *
 * @Desc: (接口名称) + impl Spring jpa会自动匹配接口
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
@Repository
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mgtl = null;

    public User findUserByIdOrUserName(Long id, String userName) {
        //创建ID查询准则
        Criteria criteriaId = Criteria.where("id").is(id);
        //创建name查询准则
        Criteria criteriaName = Criteria.where("userName").is(userName);
        Criteria criteria = new Criteria();
        //使用 Or 将其关联
        criteria.orOperator(criteriaId, criteriaName);
        Query query = Query.query(criteria);
        return mgtl.findOne(query, User.class);
    }

}
