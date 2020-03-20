package com.tianhy.springbootmongodb.repository;

import com.tianhy.springbootmongodb.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/19
 **/
@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    /**
     * 符合 Jpa 命名规范的方法，不需要实现也可以使用
    * @param userName
     * @return
     */
    public List<User> findByUserNameLike(String userName);

    //使用自定义方法
    public User findUserByIdOrUserName(Long id, String userName);
}
