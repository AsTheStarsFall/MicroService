package com.tianhy.springbootc5jdbc;

import com.tianhy.springbootc5jdbc.pojo.User;
import com.tianhy.springbootc5jdbc.service.MybatisUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootC5jdbcApplicationTests {


    @Autowired
    private MybatisUserService userService = null;

    @Test
    public void getUser(){
        User user = userService.getUser(1L);
        System.out.println(user.toString());
    }

}
