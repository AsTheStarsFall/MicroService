package com.tianhy.springmvc.boot;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/14
 **/
public class MyWebAppInitiallizer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //IOC容器配置
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{};
    }

    //DispatcherServlet URL映射
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    //DispatcherServlet 拦截请求匹配
    @Override
    protected String[] getServletMappings() {
        return new String[]{"*.do"};
    }
}
