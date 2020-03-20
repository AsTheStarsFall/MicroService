package com.tianhy.autoconfiguration;

import com.tianhy.format.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/10/3 5:11
 **/
@Configuration
public class FormatConfiguration {

    @Bean
    @ConditionalOnMissingClass("com.alibaba.fastjson.JSON")
    @Primary
    public FormatProcessor stringFormatProcessor(){
        return new StringFormatProcessor();
    }

    @Bean
    @ConditionalOnClass(name = "com.alibaba.fastjson.JSON")
    public FormatProcessor jsonFormatProcessor(){
        return new JsonFormatProcessor();
    }

}
