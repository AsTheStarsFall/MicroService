package com.tianhy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2020/3/4 9:19
 **/
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    //密码解码器
    @Bean
    PasswordEncoder passwordEncoder() {
        //不对密码进行加密
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //设置账号密码与权限
        //基于内存认证
        auth.inMemoryAuthentication()
                .withUser("admin").password("123").roles("ADMIN", "USER")
                .and()
                .withUser("tian").password("123").roles("USER");
    }

    //HttpSecurity
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                //给路径设置权限

                // 访问 admin/** 要具备 ADMIN
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/user/**")
                .access("hasAnyRole('ADMIN','USER')")
                .antMatchers("/db/**")
                .access("hasRole('ADMIN') and hasRole('DBA')")
                //访问其他任何URL也需要认证后才可以访问
                .anyRequest()
                .authenticated()
                .and()
                //开启表单登录，也可以直接调用 /login 接口，发起一个 POST 请求
                .formLogin()
                //未授权跳转到这个页面，自定义的登录页面
                .loginPage("/login_page")
                //loginProcessingUrl为了方便Ajax或者移动前端调用登录接口
                .loginProcessingUrl("/login")
                .usernameParameter("name")
                .passwordParameter("password")

                //登录成功处理 successHandler
                .successHandler((req, resp, auth) -> {
                    //当前登录用户的信息
                    Object principal = auth.getPrincipal();
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    resp.setStatus(200);
                    Map<String, Object> map = new HashMap<>();
                    map.put("status", 200);
                    map.put("msg", principal);
                    ObjectMapper om = new ObjectMapper();
                    out.write(om.writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                //成功后跳转
//                .successForwardUrl("/hello")
                // 登录失败处理 failureHandler
                .failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    resp.setStatus(401);
                    Map<String, Object> map = new HashMap<>();
                    map.put("status", 401);
                    if (e instanceof LockedException) {
                        map.put("msg", "账户被锁定，登录失败!");
                    } else if (e instanceof BadCredentialsException) {
                        map.put("msg", "账户名或密码输入错误，登录失败!");
                    } else if (e instanceof DisabledException) {
                        map.put("msg", "账户被禁用，登录失败!");
                    } else if (e instanceof AccountExpiredException) {
                        map.put("msg", "账户已过期，登录失败!");
                    } else if (e instanceof CredentialsExpiredException) {
                        map.put("msg", "密码已过期，登录失败!");
                    } else {
                        map.put("msg", "登录失败!");
                    }
                    ObjectMapper om = new ObjectMapper();
                    out.write(om.writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                //和登录相关的接口。都不需要认证即可访问
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler((req, resp, auth) -> {


                })

                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req,
                                                HttpServletResponse resp,
                                                Authentication auth) throws IOException, ServletException {

                        resp.sendRedirect("/login_page");
                    }
                })

                .and()
                .csrf()
                .disable();


    }
}
