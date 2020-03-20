package com.tianhy.springbootsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@SpringBootApplication
@MapperScan(
        basePackages = "com.tianhy.springbootsecurity.dao",
        annotationClass = Repository.class
)
@EnableCaching
public class SpringbootSecurityApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurityApplication.class, args);
    }

    //数据库验证
    @Autowired
    private DataSource dataSource = null;

    //钥匙
    @Value("${system.user.password.secret}")
    private String secret = null;


    //自定义UserDetailsService
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisTemplate redisTemplate;

    //    //通过用户名查询密码
    String pwd = "select user_name,pwd,available from t_user where user_name = ?";
    //通过用户名查询角色信息
    String role = "select u.user_name,r.role_name from t_user u,t_user_role ur,t_role r" +
            "where u.id = ur.user_id and r.id = ur.role_id and u-=user_name = ?";

    //用户登陆的权限
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码加密器
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(this.secret);
//        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authentication =
//                auth.inMemoryAuthentication().passwordEncoder(passwordEncoder);
//        authentication.withUser("admin")
//                //密码
//                .password(passwordEncoder.encode("abc"))
//                //角色权限
//                .authorities("ROLE_USER", "ROLE_ADMIN");
//        authentication.withUser("myuser")
//                .password(passwordEncoder.encode("123456"))
//                .authorities("ROLE_USER");


        //todo 从数据库查询
//        auth.jdbcAuthentication().passwordEncoder(passwordEncoder)
//                .dataSource(dataSource)
//                .usersByUsernameQuery(pwd)
//                .authoritiesByUsernameQuery(role);

        //设置用户密码服务和密码编码器
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    //访问权限
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //只要通过验证就能访问所有的请求
        // authorizeRequests 方法限定只对签名成功的用户请求
        // anyRequest 方法限定所有请求
        // authenticated 方法对所有签名成功的用户允许方法
//        http.authorizeRequests().anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and().httpBasic();

        //配置访问权限
//        http.authorizeRequests()
//                //限定 '/user/welcome' 请求角色 ROLE_USER or ROLE_ADMIN
//                .antMatchers("/user/welcome", "/user/details").hasAnyRole("USER", "ADMIN")
//                //限定 '/admin/**' 下所有请求权限赋予ROLE_ADMIN
//                .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
//                //其他路径允许签名后访问
//                .anyRequest().permitAll()
//                //对于其他没有配置权限的访问允许匿名访问
//                .and().anonymous()
//                //Spring Secutity 默认的登陆页面
//                .and().formLogin()
//                .and().httpBasic();

        //Spring 表达式设置权限
//        http.authorizeRequests()
//                //限定 '/user' 下的请求只有角色 ROLE_USER or ROLE_ADMIN
//                .antMatchers("/user/**").access("hasRole('USER') or hasRole('ADMIN')")
//                //限定访问权限赋予ROLE_ADMIN 要求是完整登陆（非记住我登陆）
//                .antMatchers("/admin/welcome1").access("hasAnyAuthority('ROLE_ADMIN') && isFullyAuthenticated()")
//                //限定 '/admin/welcome2' 访问权限给角色 ROLE_ADMIN，允许不完整登陆
//                .antMatchers("/admin/welcome2").access("hasAnyAuthority('ROLE_ADMIN')")
//                //使用记住我登陆功能
//                .and().rememberMe()
//                .and().formLogin()
//                .and().httpBasic();


        //强制使用HTTPS请求
        //使用安全渠道，限定为Https
//        http.requiresChannel().antMatchers("/admin/**").requiresSecure()
//                //不使用Https
//                .and().requiresChannel().antMatchers("/user/**").requiresInsecure()
//                //限定允许访问的角色
//                .and().authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN")
//                .antMatchers("/user/**").hasAnyRole("ROLE", "ADMIN");

        //自定义登陆页面
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')")
                //启用记住我
//                .and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key")
//                .and().httpBasic().realmName("my-basic-name")
////                通过签名后可以访问任何
//                .and().authorizeRequests().antMatchers("/**").permitAll()
//                //设置登陆页和默认的跳转路径
//                .and().formLogin()
//                .loginPage("/login/page").defaultSuccessUrl("/").failureUrl("/error.html")
//                .and()
//                .logout().logoutUrl("/logout/page").logoutSuccessUrl("/");


                //
                .and()
                .formLogin()
                .loginPage("/login/page")
                .failureUrl("/error.html")
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

    //自定义初始化方法
    @PostConstruct
    public void initRedis() {
        initRdisTemplate();
    }

    //设置序列化器
    public void initRdisTemplate() {
        RedisSerializer serializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
    }
}
