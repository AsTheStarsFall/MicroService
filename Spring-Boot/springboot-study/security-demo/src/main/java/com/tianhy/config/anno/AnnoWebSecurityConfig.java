package com.tianhy.config.anno;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * {@link}
 *
 * @Desc: 注解的方式
 * @Author: thy
 * @CreateTime: 2020/3/5 1:06
 **/
@Configuration
//在方法执行前进行验证
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class AnnoWebSecurityConfig {
}
