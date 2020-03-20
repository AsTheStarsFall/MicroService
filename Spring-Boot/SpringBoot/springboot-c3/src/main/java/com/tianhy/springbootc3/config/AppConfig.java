package com.tianhy.springbootc3.config;

import com.tianhy.springbootc3.pojo.definition.Animal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/15
 **/
//代表这是一个java配置，spring会根据它来生成一个IOC ，to装配Bean
@Configuration
@Slf4j
/**
 * 如果不加包名或者类名，扫描AppConfig所在包和子包
 * 3 种扫描的方法
 */
//@ComponentScan(basePackages = "com.tianhy.springbootc3.*",
//        //过滤掉UserService
//        excludeFilters = {@ComponentScan.Filter(classes = {UserService.class})})


//lazyInit = true 开启延迟依赖注入
//@ComponentScan(basePackages = "com.tianhy.springbootc3", lazyInit = true,
//        //过滤掉@Service
//        excludeFilters = {@ComponentScan.Filter(classes = {Service.class})})


//@ComponentScan(basePackages = {"com.tianhy.springbootc3.c3.pojo"})
//@ComponentScan(basePackageClasses ={User.class} )
@ComponentScan(basePackages = "com.tianhy.springbootc3.*")
@ImportResource(value = {"classpath:spring-other.xml"})
public class AppConfig {

    //Bean注解将这个方法返回的user装配到IOC，它的属性name 定义这个Bean的名称
    //如果找不到，就按照方法名 initUser 装配
//    @Bean(name = "user")
//    public User initUser(){
//        User user = new User();
//        user.setId(1L);
//        user.setUserName("user_name_1");
//        user.setNote("note_1");
//        return user;
//    }

//    private static final ResourceBundle JDBC;
//
//    static {
//        JDBC =ResourceBundle.getBundle("");
//    }

//    // 第三方的Bean，数据源
    @Bean(name = "dataSource")
    @Profile("dev")
    public DataSource getDevDataSource(){
        log.info("开发数据库");
        Properties props = new Properties();
        props.setProperty("driver", "com.mysql.jdbc.Driver");
        props.setProperty("url", "jdbc:mysql://localhost:3306/dev");
        props.setProperty("username", "root");
        props.setProperty("password", "root");

        DataSource dataSource = null;
        try {
            //根据Properties 中的配置，BasicDataSourceFactory 工厂创建一个dataSource
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean(name = "dataSource")
    @Profile("test")
    public DataSource getTestDataSource(){
        log.info("测试数据库");
        Properties props = new Properties();
        props.setProperty("driver", "com.mysql.jdbc.Driver");
        props.setProperty("url", "jdbc:mysql://localhost:3306/test");
        props.setProperty("username", "root");
        props.setProperty("password", "root");

        DataSource dataSource = null;
        try {
            //根据Properties 中的配置，BasicDataSourceFactory 工厂创建一个dataSource
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     * 条件装配，当数据库连接池连接不上，IOC会抛出异常，不让IOC装配数据源
     */
//    @Bean(name = "dataSource",destroyMethod = "close")
//    @Conditional(DatabaseConditional.class)
//    public DataSource getDataSource(
//            @Value("${jdbc.driverName}") String driver,
//			@Value("${jdbc.url}") String url,
//			@Value("${jdbc.username}") String username,
//			@Value("${jdbc.password}") String password
//    ) {
//        Properties props = new Properties();
//        props.setProperty("driver", driver);
//        props.setProperty("url", url);
//        props.setProperty("username", username);
//        props.setProperty("password", password);
//
//        DataSource dataSource = null;
//        try {
//            //根据Properties 中的配置，BasicDataSourceFactory 工厂创建一个dataSource
//            dataSource = BasicDataSourceFactory.createDataSource(props);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dataSource;
//    }


}
