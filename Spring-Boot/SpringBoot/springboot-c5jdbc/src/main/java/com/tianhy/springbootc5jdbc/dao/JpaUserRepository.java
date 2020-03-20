package com.tianhy.springbootc5jdbc.dao;

import com.tianhy.springbootc5jdbc.pojo.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * {@link}
 *
 * @Desc: 定义JPA接口
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
public interface JpaUserRepository extends JpaRepository<User, Long> {


    @Query("FROM user WHERE user_name LIKE CONCAT('%',?1,'%') AND note LIKE CONCAT('%',?2,'%') ")
    public List<User> findUsers(String userName, String note);

    /**
     * 按用户名称模糊查询
     *
     * @param userName 用户名
     * @return 用户列表
     */
    List<User> findByUserNameLike(String userName);

    /**
     * 根据主键查询
     *
     * @param id -- 主键
     * @return 用户
     */
    User getUserById(Long id);

    /**
     * 按照用户名称或者备注进行模糊查询
     *
     * @param userName 用户名
     * @param note     备注
     * @return 用户列表
     */
    List<User> findByUserNameLikeOrNoteLike(String userName, String note);
}
