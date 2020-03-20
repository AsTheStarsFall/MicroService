package com.tianhy.springbootsecurity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/21
 **/
public class Test {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("uvxyz");
//        String admin = passwordEncoder.encode("admin");
        String user = passwordEncoder.encode("admin");

//        System.out.println(admin);
        System.out.println(user);

    }
}
