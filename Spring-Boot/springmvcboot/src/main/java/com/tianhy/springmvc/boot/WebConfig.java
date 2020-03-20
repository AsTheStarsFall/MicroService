package com.tianhy.springmvc.boot;

import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/14
 **/
@Configuration
//定义SpringMVC扫描的包
@ComponentScan(value = "com.*",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)}
)
//启动SpringMVC
@EnableWebMvc
public class WebConfig {

    /**
    * @Description: 通过Bean注解初始化视图解析器
    * @return: org.springframework.web.servlet.ViewResolver
    */
    @Bean(name = "internalResourceViewResolver")
    public ViewResolver initViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //前缀
        viewResolver.setPrefix("/WEB-INF/jsp/");
        //后缀
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(name = "requestMappingHandlerAdapter")
    public HandlerAdapter initRequestMappingHandlerAdapter(){
        //映射器适配器
        RequestMappingHandlerAdapter rmhd = new RequestMappingHandlerAdapter();

        //HTTP JSNO转换器
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType mediaType = MediaType.APPLICATION_JSON_UTF8;
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(mediaType);

        //设置转换器支持的类型
        jsonConverter.setSupportedMediaTypes(mediaTypes);
        rmhd.getMessageConverters().add(jsonConverter);
        return rmhd;
    }

}
