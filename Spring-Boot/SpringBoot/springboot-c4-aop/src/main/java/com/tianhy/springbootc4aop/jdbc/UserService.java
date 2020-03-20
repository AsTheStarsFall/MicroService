package com.tianhy.springbootc4aop.jdbc;

import com.tianhy.springbootc4aop.jdbc.dao.UserDao;
import com.tianhy.springbootc4aop.jdbc.pojo.User;

import java.sql.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
public class UserService {

    public int insertUser() {
        UserDao userDao = new UserDao();
        User user = new User();

        user.setUser_name("uname1");
        user.setPassword("pas");
        user.setAge(18);
        Connection conn = null;
        int result = 0;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            conn.setAutoCommit(false);
            result = userDao.insertUser(conn, user);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
