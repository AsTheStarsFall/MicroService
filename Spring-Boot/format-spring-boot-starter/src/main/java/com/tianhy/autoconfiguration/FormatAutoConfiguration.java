package com.tianhy.autoconfiguration;

import com.tianhy.FormatTemplate;
import com.tianhy.format.FormatProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

/**
 * {@link}
 *
 * @Desc: 自动装配配置类，在spring.factories中指定此类进行自动装配
 * @Author: thy
 * @CreateTime: 2019/10/3 5:17
 **/
@Configuration
@Import(FormatConfiguration.class)
/**
 * 可以通过配置文件装配，指定配置类为 FormatPropterties
 */
@EnableConfigurationProperties(FormatPropterties.class)
public class FormatAutoConfiguration {

    @Bean
    public FormatTemplate formatTemplate(FormatProcessor formatProcessor, FormatPropterties formatPropterties) {
        return new FormatTemplate(formatProcessor, formatPropterties);

    }
}
