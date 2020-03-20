package com.tianhy.springbootc3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//PropertySource 定义对应的属性properties
@PropertySource(value = {"classpath:jdbc.properties"},ignoreResourceNotFound = true)
public class SpringbootC3ApplicationTests {

    @Test
    public void contextLoads() {
    }

}
