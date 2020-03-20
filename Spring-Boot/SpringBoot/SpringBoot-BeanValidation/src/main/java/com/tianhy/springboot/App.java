package com.tianhy.springboot;

import com.tianhy.springboot.web.UserControllerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App extends WebMvcConfigurerAdapter
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserControllerInterceptor());
    }
}
