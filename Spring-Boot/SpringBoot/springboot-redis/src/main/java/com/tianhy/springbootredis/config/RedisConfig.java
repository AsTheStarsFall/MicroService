package com.tianhy.springbootredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/17
 **/
@Configuration
public class RedisConfig{

    //连接工厂
    private RedisConnectionFactory redisConnectionFactory = null;

    /**
     * 初始化Redis连接池
     * @return
     */
    @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory(){
        if(this.redisConnectionFactory!=null){
            return this.redisConnectionFactory;
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲数
        jedisPoolConfig.setMaxIdle(30);
        //最大连接数
        jedisPoolConfig.setMaxTotal(50);
        //最大等待
        jedisPoolConfig.setMaxWaitMillis(2000);

        //Jedis连接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        //获取单机的Redis的 配置
        RedisStandaloneConfiguration rsclf = connectionFactory.getStandaloneConfiguration();
        rsclf.setHostName("127.0.0.1");
        rsclf.setPort(6379);
//        rsclf.setPassword(RedisPassword.of("123456"));
        this.redisConnectionFactory = connectionFactory;

        return connectionFactory;
    }

    //创建RedisTemplate
    @Bean(name = "redisTemplate")
    public RedisTemplate<Object,Object> initRedisTemplate(){
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        //字符串序列化器
        RedisSerializer redisSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }

}
