package com.tianhy.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/2 14:43
 **/
@Configuration
public class CoreConfiguration {
    @Bean
    public Core core(){
        return new Core();
    }
}
