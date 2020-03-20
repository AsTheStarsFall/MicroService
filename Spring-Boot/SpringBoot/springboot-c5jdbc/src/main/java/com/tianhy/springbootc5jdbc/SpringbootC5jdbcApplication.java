package com.tianhy.springbootc5jdbc;

import com.tianhy.springbootc5jdbc.dao.MybatisUserDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@SpringBootApplication(scanBasePackages = "com.tianhy.springbootc5jdbc")
//定义JPA接口扫描包路径
@EnableJpaRepositories(basePackages = "com.tianhy.springbootc5jdbc.dao")
//定义实体Bean扫描包路径
@EntityScan(basePackages = "com.tianhy.springbootc5jdbc.pojo")
//Mybatis扫描的包
@MapperScan(
        //指定扫描包
        basePackages = "com.tianhy.springbootc5jdbc.*",
        //指定sqlSessionFactory
        sqlSessionFactoryRef = "sqlSessionFactory",
        //指定sqlSessionTemplate 将忽略sqlSessionFactory配置
        sqlSessionTemplateRef = "sqlSessionTemplate",
        annotationClass = Repository.class
)
public class SpringbootC5jdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootC5jdbcApplication.class, args);
    }

//    @Autowired
//    SqlSessionFactory sqlSessionFactory = null;
//
//    @Bean
//    public MapperFactoryBean<MybatisUserDao> initMybatisUserDao() {
//        MapperFactoryBean<MybatisUserDao> bean = new MapperFactoryBean<>();
//        bean.setMapperInterface(MybatisUserDao.class);
//        bean.setSqlSessionFactory(sqlSessionFactory);
//        return bean;
//    }


//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer(){
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        //加载SqlSessionFactory SpringBoot会自动生成SqlSessionFactory的实例
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        //定义扫描的包
//        mapperScannerConfigurer.setBasePackage("com.tianhy.springbootc5jdbc.*");
//        //限定被标注了@Repository的接口才会被扫描
//        mapperScannerConfigurer.setAnnotationClass(Repository.class);
//        return mapperScannerConfigurer;
//    }


}
