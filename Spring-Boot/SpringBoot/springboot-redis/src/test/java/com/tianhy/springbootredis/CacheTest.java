package com.tianhy.springbootredis;

import com.tianhy.springbootredis.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/18
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class CacheTest {

    @Autowired
    private TestRestTemplate restTemplate = null;


    @Test
    public void insertCacheTest() {
//        User forObject = restTemplate.getForObject("/cache/user/insert", User.class, "name_cache1", "note_cache1", 1);
        Map<String,Object> map = new HashMap<>();
        map.put("userName","caache_name1");
        map.put("note","cache_note");
        map.put("sex",1);
        ResponseEntity<User> forEntity = restTemplate.getForEntity("/cache/user/insert", User.class, map);
        log.info(forEntity.toString());
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void redisHash(){
        Map<String,String> map = new HashMap<>();
        map.put("stock","30000");
        map.put("price","3000");
        stringRedisTemplate.opsForHash().putAll("product_1",map);
    }
}
