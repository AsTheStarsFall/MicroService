package com.tianhy.springbootredis.mapper;

import com.tianhy.springbootredis.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/18
 **/
@Repository
public interface UserMapper {
    public User getUser(Long id);

    public int insertUser(User user);

    public int deleteUser(Long id);

    public int updateUser(User user);

    public int batchInsert(List<User> list);

    public List<User> selectBySex(int sex);

    public List<User> selectAll(RowBounds rowBounds);

    public List<User> selectListLimit(Map<String,Object> limit);

    public List<User> findUsers(@Param("userName") String userName, @Param(("note")) String note);

}
