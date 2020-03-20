package com.tianhy.springbootmybatis;

import com.tianhy.springbootmybatis.plugin.*;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Properties;

@SpringBootApplication
@MapperScan(
        //指定扫描包
        basePackages = "com.tianhy.springbootmybatis.*",
        //指定sqlSessionFactory
        sqlSessionFactoryRef = "sqlSessionFactory",
        //指定sqlSessionTemplate 将忽略sqlSessionFactory配置
        sqlSessionTemplateRef = "sqlSessionTemplate",
        annotationClass = Repository.class
)
public class SpringbootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisApplication.class, args);
    }


//    //以代码的形式配置拦截器
//    @Autowired
//    private SqlSessionFactory sqlSessionFactory = null;
//
//    @PostConstruct
//    public void initMybatis(){
//        Interceptor myPlugin = new MyPlugin();
//        Interceptor sqlInteceptor = new SqlInteceptor();
//        Interceptor executorInteceptor = new ExecutorInteceptor();
//        Properties properties = new Properties();
//        properties.setProperty("key1","value1");
//        myPlugin.setProperties(properties);
//        sqlSessionFactory.getConfiguration().addInterceptor(myPlugin);
//        sqlSessionFactory.getConfiguration().addInterceptor(sqlInteceptor);
//        sqlSessionFactory.getConfiguration().addInterceptor(executorInteceptor);
//
//
//    }

}
