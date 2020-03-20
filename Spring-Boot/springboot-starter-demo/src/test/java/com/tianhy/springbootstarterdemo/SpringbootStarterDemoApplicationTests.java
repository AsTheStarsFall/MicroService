package com.tianhy.springbootstarterdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import sun.security.pkcs11.Secmod;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootStarterDemoApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    JdbcTemplate db1JdbcTemplate;

    @Test
    public void testDatasource(){
        String sql = "insert into user_info(user_name,age) values('tianhy3',18)";
        db1JdbcTemplate.execute(sql);

    }

}
