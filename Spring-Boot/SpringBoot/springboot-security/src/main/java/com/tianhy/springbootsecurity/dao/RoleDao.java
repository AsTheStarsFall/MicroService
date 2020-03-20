package com.tianhy.springbootsecurity.dao;

import com.tianhy.springbootsecurity.pojo.DataBaseRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
@Mapper
@Repository
public interface RoleDao {
    public List<DataBaseRole> findRolesByUserName(String userName);
}
