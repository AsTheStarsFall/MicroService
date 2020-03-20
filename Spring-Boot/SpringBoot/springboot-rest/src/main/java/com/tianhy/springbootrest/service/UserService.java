package com.tianhy.springbootrest.service;

import com.tianhy.springbootrest.pojo.User;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
public interface UserService {
    public User getUser(Long id);
    public int batchInsert(List<User> list);
    public List<User> selectBySex(int sex);
    public  List<User> selectAll(RowBounds rowBounds);
    public List<User> selectListLimit(int start, int pageSize);
    public int insertUser(User user);
    public User updateUser(User user);



}
