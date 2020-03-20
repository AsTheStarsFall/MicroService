package com.tianhy.springbootwebsocket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootWebsocketApplicationTests {

    @Test
    public void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String p1 = passwordEncoder.encode("p1");
        System.out.println(p1);
    }

}
