package com.tianhy.springbootredis;

import com.tianhy.springbootredis.config.RedisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/17
 **/
public class RedisMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);

//        //字符串操作
//        redisTemplate.opsForValue().set("key1","value1");
//        //散列操作
//        redisTemplate.opsForHash().put("hash","field","hvalue");
//        //获取List列表操作接口
//        ListOperations listOperations = redisTemplate.opsForList();
//        //获取set集合操作接口
//        SetOperations setOperations = redisTemplate.opsForSet();
//        //获取zset有序集合操作接口
//        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
//        //获取地理位置操作接口
//        redisTemplate.opsForGeo();
//        //获取基数操作接口
//        redisTemplate.opsForHyperLogLog();
//
//        //bound 绑定键操作接口
//        //获取地理位置绑定键操作接口
//        BoundGeoOperations geo = redisTemplate.boundGeoOps("geo");
//        //获取散列绑定键操作接口
//        BoundHashOperations hash = redisTemplate.boundHashOps("hash");
//        //获取列表（链表）绑定键操作接口
//        BoundListOperations list = redisTemplate.boundListOps("list");
//        //获取集合绑定键操作接口
//        BoundSetOperations set = redisTemplate.boundSetOps("set");
//        //获取有序集合绑定键操作接口
//        BoundZSetOperations zset = redisTemplate.boundZSetOps("zset");

        //获取到接口以后，就可以对某个键的数据进行多次操作

    }

    //一条连接处理多条命令
    public void userSessionCallbck(RedisTemplate redisTemplate){
        new SessionCallback() {
            @Override
            public Object execute(RedisOperations op) throws DataAccessException {
                op.opsForValue().set("key1", "value1");
                op.opsForHash().put("hash", "field", "hvalue");
                return null;
            }
        }.execute(redisTemplate);
    }
}
