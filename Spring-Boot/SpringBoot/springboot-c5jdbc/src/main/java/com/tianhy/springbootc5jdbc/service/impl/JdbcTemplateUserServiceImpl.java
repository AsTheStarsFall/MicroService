package com.tianhy.springbootc5jdbc.service.impl;

import com.tianhy.springbootc5jdbc.enumeration.SexEnum;
import com.tianhy.springbootc5jdbc.pojo.User;
import com.tianhy.springbootc5jdbc.service.JdbcTemplateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import javax.websocket.OnError;
import java.sql.*;
import java.util.List;

/**
 * {@link}
 *
 * @Desc: JdbcTemplate操作数据库
 * query与update是使用不同的数据库连接完成的
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@Service
public class JdbcTemplateUserServiceImpl implements JdbcTemplateUserService {

    /**
     * spring boot 自动配置机制配置好了jdbcTemplate，是由Spring提供的
     */
    @Autowired
    private JdbcTemplate jdbcTemplate = null;

    //映射关系
    private RowMapper<User> getUserMapper() {
//        RowMapper<User> userRowMapper = new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return null;
//            }
//        };
//        return null;

        RowMapper<User> userRowMapper = (ResultSet rs, int rownum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("user_name"));
            user.setNote(rs.getString("note"));
            int sexid = rs.getInt("sex");
            SexEnum sexEnum = SexEnum.getSexEnumById(sexid);
            user.setSex(sexEnum);
            return user;
        };

        return userRowMapper;
    }

    @Override
    public User getUserById(Long id) {
        String sql = "SELECT id,user_name,note,sex FROM t_user WHERE id = ?";
        Object[] params = new Object[]{id};

//        Object object = jdbcTemplate.queryForObject(sql, params, new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return null;
//            }
//        });

        User user = jdbcTemplate.queryForObject(sql, params, getUserMapper());
        return user;
    }

    @Override
    public List<User> findUsers(String userName, String note) {

        String sql = "SELECT id,user_name,note,sex FROM t_user WHERE user_name LIKE CONCAT('%',?,'%')" +
                "AND note LIKE CONCAT('%',?,'%')";

        Object[] params = new Object[]{userName, note};
        List<User> query = jdbcTemplate.query(sql, params, getUserMapper());
        return query;
    }

    @Override
    public int insertUser(User user) {
        String sql = "INSERT INTO t_user (user_name,note,sex) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, user.getUserName(), user.getNote(), user.getSex().getId());
    }

    @Override
    public int updateUser(User user) {
        String sql = "UPDATE t_user SET user_name = ?, note = ?,sex = ? WHERE id = ?";
        return jdbcTemplate.update(sql, user.getUserName(), user.getNote(), user.getSex().getId(), user.getId());
    }

    @Override
    public int deleteUser(Long id) {
        String sql = "DELETE FROM t_user WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    //希望在一条数据库连接中执行多条SQL
    //通过StatementCallback,ConnectionCallback执行多条SQL
    @Override
    public User getUser1(Long id) {
//        this.jdbcTemplate.execute(new StatementCallback<User>() {
//            @Override
//            public User doInStatement(Statement stmt) throws SQLException, DataAccessException {
//                return null;
//            }
//        });

        //通过StatementCallback
        User result = this.jdbcTemplate.execute((Statement stmt) -> {

            //statement 直接与SQL拼接
            String sql = "SELECT COUNT(*) total FROM t_user WHERE id = " + id;
            //直接执行SQL
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                int total = resultSet.getInt("total");
                System.out.println(total);
            }

            User user = null;
            String sql1 = "SELECT id,user_name,note,sex FROM t_user WHERE id = " + id;
            ResultSet resultSet1 = stmt.executeQuery(sql1);
            while (resultSet1.next()) {
                //获得行数
                int rowNum = resultSet1.getRow();
                user = getUserMapper().mapRow(resultSet1, rowNum);
            }
            return user;
        });
        return result;
    }


    //通过ConnectionCallback
    @Override
    public User getUser2(Long id) {
//        this.jdbcTemplate.execute(new ConnectionCallback<User>() {
//            @Override
//            public User doInConnection(Connection con) throws SQLException, DataAccessException {
//                return null;
//            }
//        });

        User result = this.jdbcTemplate.execute((Connection conn) -> {
            String sql = "SELECT COUNT(*) as total FROM t_user WHERE id = ?";
            //创建语句集
            PreparedStatement pstm = conn.prepareStatement(sql);
            //入参处理
            pstm.setLong(1,id);
            //执行返回结果集
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                int total = resultSet.getInt("total");
                System.out.println(total);
            }
            String sql1 = "SELECT id,user_name,note,sex FROM t_user WHERE id = ?";
            //创建语句集
            PreparedStatement pstm1 = conn.prepareStatement(sql1);
            //入参处理
            pstm1.setLong(1,id);
            //执行返回结果集
            ResultSet resultSet1 = pstm1.executeQuery();
            User user = null;
            while (resultSet1.next()){
                int row = resultSet1.getRow();
                user = getUserMapper().mapRow(resultSet1,row);
            }
            return user;
        });
        return result;
    }
}
