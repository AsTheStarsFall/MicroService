package com.tianhy.springbootjdbc.repository;

import com.tianhy.springbootjdbc.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.Collections;

/**
 * {@link com.tianhy.springbootjdbc.domain.User}
 *
 * @Desc: 用户仓储（SQL/NoSQL/内存型）
 * @Author: thy
 * @CreateTime: 2019/4/11
 **/
@Repository
public class UserRepository {

    private final DataSource dataSource;
    private final DataSource masterDataSource;
    private final DataSource slaveDataSource;
    private final JdbcTemplate jdbcTemplate;
    //API的方式
    private final PlatformTransactionManager platformTransactionManager;


    //@Qualifier 以BeanName的方式注入
    public UserRepository(DataSource dataSource,
                          @Qualifier("masterDataSource") DataSource masterDataSource,
                          @Qualifier("slaveDataSource") DataSource slaveDataSource,
                          JdbcTemplate jdbcTemplate, PlatformTransactionManager platformTransactionManager) {
        this.dataSource = dataSource;
        this.masterDataSource = masterDataSource;
        this.slaveDataSource = slaveDataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.platformTransactionManager = platformTransactionManager;
    }

    public Collection<User> findAll() {
        return Collections.emptyList();
    }

    /**
     * 事务注解
     */
    @Transactional
    public Boolean transactionalSave(User user) {
        boolean success = false;
        success = jdbcTemplate.execute("INSERT INTO user (id,user_name,age,password) VALUES (?,?,?,?);",
                (PreparedStatementCallback<Boolean>) preparedStatement -> {
                    preparedStatement.setInt(1, user.getId());
                    preparedStatement.setString(2, user.getUser_name());
                    preparedStatement.setInt(3, user.getAge());
                    preparedStatement.setString(4, user.getPassword());
                    return preparedStatement.executeUpdate() > 0;
                });
        return success;
    }


    //自动提交模式
    public Boolean save(User user) {
        boolean success = false;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO user (id,user_name,age,password) VALUES (?,?,?,?);");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUser_name());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getPassword());
            success = preparedStatement.executeUpdate() > 0;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.commit();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.printf("[Thread : %s ]save user : %s \n", Thread.currentThread().getName(), user);
        return success;
    }

    // API的方式处理事务
    public Boolean PlatformTransSave(User user) {
        boolean success = false;
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(defaultTransactionDefinition);
        success = jdbcTemplate.execute("INSERT INTO user (id,user_name,age,password) VALUES (?,?,?,?);",
                new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getUser_name());
                preparedStatement.setInt(3, user.getAge());
                preparedStatement.setString(4, user.getPassword());
                return preparedStatement.executeUpdate() > 0;
            }
        });
        platformTransactionManager.commit(transactionStatus);
        return success;
    }
}
