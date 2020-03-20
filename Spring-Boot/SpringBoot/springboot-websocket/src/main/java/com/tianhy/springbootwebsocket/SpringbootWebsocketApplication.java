package com.tianhy.springbootwebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringbootWebsocketApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebsocketApplication.class, args);
    }


    //密码解码器
    @Bean
    PasswordEncoder passwordEncoder() {
        //不对密码进行加密
        return NoOpPasswordEncoder.getInstance();
    }

    //定义3个内存用户
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码加密器
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //3个内存用户，密码分别为 加密后的p1 p2 p3
//        System.out.println("用户user1，密码p1 加密后：" + passwordEncoder.encode("p1"));
//        System.out.println("用户user2，密码p2 加密后：" + passwordEncoder.encode("p2"));
//        System.out.println("用户user3，密码p3 加密后：" + passwordEncoder.encode("p3"));

        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("user")
                .roles("USER")
                .and()
                .withUser("user2")
                .password("user")
                .roles("ADMIN")
                .and()
                .withUser("user3")
                .password("user")
                .roles("USER");

    }
}
