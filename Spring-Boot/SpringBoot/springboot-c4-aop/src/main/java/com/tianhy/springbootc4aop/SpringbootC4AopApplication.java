package com.tianhy.springbootc4aop;

import com.tianhy.springbootc4aop.aspect.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.tianhy.springbootc4aop.aspect"})
public class SpringbootC4AopApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootC4AopApplication.class, args);
    }

    @Bean(name = "myAspect")
    public MyAspect initAspect() {
        return new MyAspect();
    }

    @Bean(name = "myAspect1")
    public MyAspect1 initAspect1() {
        return new MyAspect1();
    }

    @Bean(name = "myAspect2")
    public MyAspect2 initAspect2() {
        return new MyAspect2();
    }

    @Bean(name = "myAspect3")
    public MyAspect3 initAspect3() {
        return new MyAspect3();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootC4AopApplication.class);
    }
}
