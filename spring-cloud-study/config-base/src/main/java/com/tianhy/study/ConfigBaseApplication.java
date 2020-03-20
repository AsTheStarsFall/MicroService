package com.tianhy.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ConfigBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigBaseApplication.class, args);

/*        SpringApplication springApplication = new SpringApplication(ConfigBaseApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);*/
    }


    //自定义配置，需要配合SPI机制
    @Configuration
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public static class MyPropertySourceLocator implements PropertySourceLocator {
        @Override
        public PropertySource<?> locate(Environment environment) {
            //自定义source
            Map<String, Object> source = new HashMap<>();
            source.put("server.port", 9090);
            MapPropertySource mapPropertySource =
                    new MapPropertySource("my-property-source", source);
            return mapPropertySource;
        }
    }

}
