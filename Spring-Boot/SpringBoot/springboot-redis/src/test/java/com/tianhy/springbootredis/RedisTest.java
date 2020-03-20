package com.tianhy.springbootredis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/27
 **/
public class RedisTest {

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
