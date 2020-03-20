package com.tianhy.springbootc4aop.jdbc.dao;

import com.tianhy.springbootc4aop.jdbc.pojo.User;

import java.sql.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
public class UserDao {

    public int insertUser(Connection conn, User user) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO user(user_name,password,age) VALUES(?,?,?)");
//            ps.setInt(1,user.getId());
            ps.setString(1, user.getUser_name());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getAge());
            return ps.executeUpdate();
        } finally {
            ps.close();
        }
    }
}
