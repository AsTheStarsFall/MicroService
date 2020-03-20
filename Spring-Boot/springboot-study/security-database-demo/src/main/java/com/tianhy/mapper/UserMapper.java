package com.tianhy.mapper;

import com.tianhy.pojo.Role;
import com.tianhy.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/5 1:45
 **/
@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);
    List<Role> getUserRolesByUid(Integer id);
}
