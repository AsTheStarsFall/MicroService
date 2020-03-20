package com.tianhy.springbootsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * {@link}
 *
 * @Desc: 新增映射关系
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //添加映射关系
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login/page").setViewName("login");
        registry.addViewController("/logout/page").setViewName("logout");
        registry.addViewController("/logout").setViewName("logout_welcome");
    }
}
