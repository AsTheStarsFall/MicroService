package com.tianhy.springbootredis;

import com.tianhy.springbootredis.enumeration.SexEnum;
import com.tianhy.springbootredis.pojo.User;
import com.tianhy.springbootredis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class SpringbootRedisApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate = null;

    @Autowired
    private UserService userService = null;


    //lua脚本测试
    @Test
    public void luaTest() {
        String forObject = restTemplate.getForObject("/redis/lua", String.class);
        log.info(forObject);
    }

    @Test
    public void lua1Test() {
        String forObject = restTemplate.getForObject("/redis/lua1", String.class, "lua1", "lua2", "lua1v", "lua2v");
        log.info(Json.toJson(forObject));
    }

    @Test
    public void insertUser() {
        User user = new User(null, "cache_name1", "cache_note1", SexEnum.FEMALE);
        log.info(userService.insertUser(user).toString());
    }

    @Test
    public void getUser() {
        log.info(userService.getUser(126L).toString());
    }

    @Test
    public void delete() {
        int i = userService.deleteUser(126L);
        log.debug("{}",i);
    }

    @Test
    public void publish(){

    }

}
