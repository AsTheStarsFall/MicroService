package com.tianhy.springwebmvc;

import com.tianhy.springwebmvc.interceptor.PersonInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.*;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SpringwebmvcApplication extends WebMvcConfigurerAdapter implements ErrorPageRegistrar {

    public static void main(String[] args) {
        SpringApplication.run(SpringwebmvcApplication.class, args);
    }

    /**
     * {@link WebMvcConfigurerAdapter}
     * 定义的拦截器需要注册到@InterceptorRegistry
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PersonInterceptor());
    }

    /**
     * {@link ErrorPageRegistrar}
     * 定义的404处理需要注册路径
     * @param registry
     */
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404"));
    }
}
