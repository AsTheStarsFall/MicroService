package com.tianhy.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @Author: thy
 * @Date: 2020/2/26 2:42
 * @Desc:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthServerAppliaction extends WebSecurityConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerAppliaction.class, args);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                //声明用户guest有读权限
                .withUser("guest").password("guest").authorities("WRIGTH_READ")
                .and()
                //声明用户admin有读写权限
                .withUser("admin").password("admin").authorities("WRIGTH_READ", "WRIGTH_WRITE");
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        //用户声明用户名和密码的加密方式，此功能在Spring Security5.0之前没有
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }


}
