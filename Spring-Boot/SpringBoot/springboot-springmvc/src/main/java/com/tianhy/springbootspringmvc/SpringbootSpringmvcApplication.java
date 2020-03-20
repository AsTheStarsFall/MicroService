package com.tianhy.springbootspringmvc;

import com.tianhy.springbootspringmvc.interceptor.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootApplication
@MapperScan(
        basePackages = "com.tianhy.springbootspringmvc.dao",
        annotationClass = Repository.class
)
public class SpringbootSpringmvcApplication implements WebMvcConfigurer {
    //extends SpringBootServletInitializer
    public static void main(String[] args) {
        SpringApplication.run(SpringbootSpringmvcApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(SpringbootSpringmvcApplication.class);
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器到SpringMVC机制
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new MyInterceptor());
        //限制连接请求
        interceptorRegistration.addPathPatterns("/interceptor/*");
        InterceptorRegistration interceptorRegistration1 = registry.addInterceptor(new MultiInterceptor1());
        interceptorRegistration1.addPathPatterns("/interceptor/*");
        InterceptorRegistration interceptorRegistration2 = registry.addInterceptor(new MultiInterceptor2());
        interceptorRegistration2.addPathPatterns("/interceptor/*");
        InterceptorRegistration interceptorRegistration3 = registry.addInterceptor(new MultiInterceptor3());
        interceptorRegistration3.addPathPatterns("/interceptor/*");

    }


}
