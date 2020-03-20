package com.tianhy.springbootredis.controller;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPSize;
import lombok.extern.slf4j.Slf4j;
import org.nutz.json.Json;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * {@link}
 *
 * @Desc:
 * @Author: thy
 * @CreateTime: 2019/5/17
 **/
@RequestMapping("/redis")
@Controller
@Slf4j
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate = null;

    //继承自RedisTemplate 只是提供字符串操作
    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;


    //字符串和散列
    @RequestMapping("/stringAndHash")
    @ResponseBody
    public Map<String, Object> testStringAndHash() {
        redisTemplate.opsForValue().set("key1", "value1");
        redisTemplate.opsForValue().set("int_key", "1");
        stringRedisTemplate.opsForValue().set("int", "1");
        //使用运算，加1操作
        redisTemplate.opsForValue().increment("int", 1);
        //获取底层连接
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();

        //减1操作 RedisTemplate不支持，所以获取底层连接再操作
        jedis.decr("int");

        Map<String, String> hash = new HashMap<>();
        hash.put("field1", "value1");
        hash.put("field2", "value2");
        //存入一个散列表类型的数据
        stringRedisTemplate.opsForHash().putAll("hash", hash);
        //新增一个字段
        stringRedisTemplate.opsForHash().put("hash", "field3", "value3");
        //绑定散列表的key，连续对同一个散列数据类型进行操作
        BoundHashOperations hashOp = stringRedisTemplate.boundHashOps("hash");
        //删除两个字段
        hashOp.delete("field1", "field2");
        //新增一个字段
        hashOp.put("hash", "value4");

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }


    //链表
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> testList() {
        //插入两个列表
        //从右往左的顺序添加
        stringRedisTemplate.opsForList().leftPushAll("list1", "v2", "v4", "v6", "v8", "v10");
        //从左往右的顺序添加
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1", "v2", "v3", "v4", "v5");
        //绑定链表list2操作
        BoundListOperations lop = stringRedisTemplate.boundListOps("list2");
        //从右边弹出一个数据
        Object result1 = lop.rightPop();
        //根据下标获取元素 v2
        Object result2 = lop.index(1);
        //从左边插入链表
        lop.leftPush("v0");
        //链表长度
        Long size = lop.size();
        //获取链表下标区间元素，整个链表的下标范围是0~size-1
        List rangeElems = lop.range(0, size - 2);
        log.info("链表list2中，下标0~{}的元素有{}", (size - 2), Json.toJson(rangeElems));
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    //集合
    @RequestMapping("/set")
    @ResponseBody
    public Map<String, Object> testSet() {
        //不允许重复值，v1只存在一个
        stringRedisTemplate.opsForSet().add("set1", "v1", "v1", "v2", "v3", "v4", "v5", "v6");
        stringRedisTemplate.opsForSet().add("set2", "v1", "v3", "v5", "v7", "v9");
        //绑定set1集合操作
        BoundSetOperations setOps = stringRedisTemplate.boundSetOps("set1");
        setOps.add("v7", "v8");
        //删除两个元素
        setOps.remove("v1", "v7");
        //返回所有元素
        Set members = setOps.members();
        log.info("集合set1中的所有元素 {}", Json.toJson(members));
        //集合元素个数
        Long set1Size = setOps.size();
        log.info("set1集合元素个数 {}", set1Size);
        //求交集, set1与set2
        Set intersect = setOps.intersect("set2");
        log.info("set1 与 set2 的交集 {}", Json.toJson(intersect));
        //求交集，并且用新的集合保存
        setOps.intersectAndStore("set2", "intersect");
        //求差集
        Set diff = setOps.diff("set2");
        log.info("set1 与 set2 的差集 {}", Json.toJson(diff));
        //求差集，并且保存到新集合
        setOps.diffAndStore("set2", "diff");
        //求并集
        Set union = setOps.union("set2");
        log.info("set1 与 set2 的并集 {}", Json.toJson(union));
        //求并集，并且保存到新的集合
        setOps.unionAndStore("set2", "union");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }


    //有序集合
    @RequestMapping("/zet")
    @ResponseBody
    public Map<String, Object> testZset() {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            double score = i * 0.1;
            //创建typeTuple对象，存入值和分数
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>("value" + i, score);
            typedTuples.add(typedTuple);
        }
        //向有序集合插入元素
        stringRedisTemplate.opsForZSet().add("zset1", typedTuples);
        //绑定zset1有序集合操作
        BoundZSetOperations<String, String> zsetOp = stringRedisTemplate.boundZSetOps("zset1");
        //添加元素
        zsetOp.add("value10", 0.26);
        //zset1 中 下标1~6区间的元素
        Set<String> range = zsetOp.range(1, 6);
        log.info("zset1有序集合中 1~6区间的元素 {}", Json.toJson(range));
        //按分数排序获取有序集合
        Set<String> rangeByScore = zsetOp.rangeByScore(0.2, 0.6);
        log.info("zset1中 按分数0.2~0.6排序{}", Json.toJson(rangeByScore));
        //定义值的范围
        RedisZSetCommands.Range range1 = new RedisZSetCommands.Range();
        //大于value3  gte大于等于 lt lte小于等于
        range1.gt("value3");
        range1.lte("value8");
        //按值排序
        Set<String> rangeByLex = zsetOp.rangeByLex(range1);
        log.info("根据定义的值的范围获得的元素 {}", Json.toJson(rangeByLex));
        //删除元素
        zsetOp.remove("value9", "value6");
        //求分数
        Double score = zsetOp.score("value8");
        log.info("value8的分数是 {}", score);
        //在指定的下标区间内排序，并返回值和分数
        Set<ZSetOperations.TypedTuple<String>> rangeWithScores = zsetOp.rangeWithScores(1, 6);
        log.info("下标区间1~6内排序后的元素的值和分数 {}", Json.toJson(rangeWithScores));
        //在指定的分数区间内排序，并返回值和分数
        Set<ZSetOperations.TypedTuple<String>> rangeByScoreWithScores = zsetOp.rangeByScoreWithScores(0.1, 0.6);
        log.info("分数区间0.1~0.6内排序后的元素的值和分数 {}", Json.toJson(rangeByScoreWithScores));
        //从大到小排序
        Set<String> reverseRange = zsetOp.reverseRange(2, 8);
        log.info("下标区间2~8内 从大到小的元素 {}", Json.toJson(reverseRange));
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }


    //Redis事务
    @RequestMapping("/multi")
    @ResponseBody
    public Map<String, Object> multi() {
        redisTemplate.opsForValue().set("key1", "value1");
//        List list = (List) redisTemplate.execute((RedisOperations operations) -> {
//            //设置监控的key
//            operations.watch("key1");
//            //开启事务
//            operations.multi();
//            operations.opsForValue().set("key2", "value2");
//            //修改key1
////            operations.opsForValue().increment("key1", 1);
//            //获取值为null，只是把命令放入队列
//            operations.opsForValue().set("key3", "value3");
//            //执行exec命令，先判断key1是否被修改过，如果没有则执行事务，否则不执行
//            return operations.exec();
//        });
//        log.info(Json.toJson(list));

        List list = (List) redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                //设置监控的key
                operations.watch("key1");
                //开启事务
                operations.multi();
                operations.opsForValue().set("key2", "value2");
                //修改key1
//            operations.opsForValue().increment("key1", 1);
                //获取值为null，只是把命令放入队列
                operations.opsForValue().set("key3", "value3");
                //执行exec命令，先判断key1是否被修改过，如果没有则执行事务，否则不执行
                return operations.exec();
            }
        });
        log.info(Json.toJson(list));
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }


    //流水线测试性能,十万次读写功能 825毫秒
    @RequestMapping("/pipeline")
    @ResponseBody
    public Map<String, Object> testPipeline() {
        Long start = System.currentTimeMillis();
//        List list = redisTemplate.executePipelined((RedisOperations operations) -> {
//            for (int i = 0; i < 100000; i++) {
////                operations.opsForValue().set("pipeline_" + i, "value_" + i);
//                operations.delete("pipeline_"+i);
////                String value= (String) operations.opsForValue().get("pipeline_"+i);
//                if(i == 100000){
//                    log.info("100000");
//                }
//            }
//            return null;
//        });

        redisTemplate.executePipelined(new SessionCallback<RedisOperations>() {
            @Override
            public RedisOperations execute(RedisOperations operations) throws DataAccessException {
                for (int i = 0; i < 100000; i++) {
//                    operations.opsForValue().set("pipeline_" + i, "value_" + i);
//                    String value = (String) operations.opsForValue().get("pipeline_" + i);
                    operations.delete("pipeline_" + i);
                    if (i == 100000) {
                        log.info("100000");
                    }
                }
                return null;
            }
        });

        Long end = System.currentTimeMillis();
        log.info("耗时 {} 毫秒", (end - start));
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }


    @RequestMapping("/lua")
    @ResponseBody
    public Map<String, Object> lua() {
        DefaultRedisScript<String> rs = new DefaultRedisScript();
        //设置脚本
        rs.setScriptText("return 'hello redis'");
        //定义返回类型
        rs.setResultType(String.class);
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();

        //执行lua脚本
        String str = (String) redisTemplate.execute(rs, stringSerializer, stringSerializer, null);
        Map<String, Object> map = new HashMap<>();
        map.put("str", str);
        return map;
    }

    @RequestMapping("/lua1")
    @ResponseBody
    public Map<String, Object> lua1(String key1, String key2, String value1, String value2) {
        // 定义Lua脚本
        //KEYS[1],KEYS[2] 代表客户端传递的第一个键和第二个键
        //ARGV[1] ARGV[2] 代表客户端传递的第一个和第二个参数
        String lua = " redis.call('set', KEYS[1], ARGV[1]) \n"
                + " redis.call('set', KEYS[2], ARGV[2]) \n"
                + " local str1 = redis.call('get', KEYS[1]) \n"
                + " local str2 = redis.call('get', KEYS[2]) \n"
                + " if str1 == str2 then  \n" + "return 1 \n"
                + " end \n"
                + " return 0 \n";
        log.info(lua);
        //结果返回long
        DefaultRedisScript<Long> rs = new DefaultRedisScript<>();
        rs.setScriptText(lua);
        rs.setResultType(Long.class);
        //字符串序列化器
        RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
        //定义Key 参数
        List<String> list = new ArrayList<>();
        list.add(key1);
        list.add(key2);
        //传递参数
        Long result = (Long) redisTemplate.execute(rs, redisSerializer, redisSerializer, list, value1, value2);
        Map<String, Object> map = new HashMap<>();
        map.put("str", result);
        return map;
    }
}
