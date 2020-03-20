package com.tianhy.springbootsecurity.service.impl;

import com.tianhy.springbootsecurity.pojo.DataBaseRole;
import com.tianhy.springbootsecurity.pojo.DataBaseUser;
import com.tianhy.springbootsecurity.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link}
 *
 * @Desc: 自定义用户认证服务
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRoleService userRoleService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //获取数据库用户信息
        DataBaseUser user = userRoleService.getUserByName(userName);
        //获取数据库角色信息
        List<DataBaseRole> roles = userRoleService.findRolesByUserName(userName);
        //将信转为UserDetails对象
        return toUserDetails(user,roles);
    }

    public UserDetails toUserDetails(DataBaseUser user,List<DataBaseRole> roles){
        //权限列表
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (DataBaseRole role : roles) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(authority);
        }
        //创建UseDetails对象，设置用户，密码，权限
        UserDetails userDetails = new User(user.getUserName(),user.getPwd(),authorities);
        return userDetails;
    }
}
