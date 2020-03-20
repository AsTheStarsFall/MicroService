package com.tianhy.thirddemo.other;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/2 13:17
 **/
@Configuration
public class OtherConfig {

    @Bean
    public OtherBean otherBean(){
        return new OtherBean();
    }
}
