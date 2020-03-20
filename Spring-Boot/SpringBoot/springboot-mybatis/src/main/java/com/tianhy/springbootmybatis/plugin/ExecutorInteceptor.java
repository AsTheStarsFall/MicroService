package com.tianhy.springbootmybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/16
 **/
@Intercepts({
        @Signature(type = Executor.class,
        method = "query",args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})
})
public class ExecutorInteceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object[] args = invocation.getArgs();
        MappedStatement mappedStatements = (MappedStatement) args[0];
        BoundSql boundSql = mappedStatements.getBoundSql(args[1]);
        String sql = boundSql.getSql();
//        System.out.println(sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
