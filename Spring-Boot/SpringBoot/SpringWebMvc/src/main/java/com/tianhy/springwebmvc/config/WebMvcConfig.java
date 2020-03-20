package com.tianhy.springwebmvc.config;

import com.tianhy.springwebmvc.converter.PersonHttpConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Description: 扩展消息类型处理
 * @Author: thy
 * @Date: 2019/4/10
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new PersonHttpConverter());
//        converters.add(new ByteArrayHttpMessageConverter());
    }
}
