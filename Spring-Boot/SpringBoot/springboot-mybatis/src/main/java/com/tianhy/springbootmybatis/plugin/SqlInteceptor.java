package com.tianhy.springbootmybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@Intercepts({
        @Signature(type = StatementHandler.class,
                method = "query",args = {Statement.class, ResultHandler.class})
})
public class SqlInteceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler sth = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = sth.getBoundSql();
        String sql = boundSql.getSql();
        try {
            return invocation.proceed();
        }finally {
//            System.out.println("------sql语句------");
//            System.out.println(sql);
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
