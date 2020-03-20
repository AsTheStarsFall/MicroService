package com.tianhy.springbootredis;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.nutz.lang.random.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.*;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.*;
import org.springframework.data.redis.serializer.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.Duration;

@SpringBootApplication
@MapperScan(basePackages = "com.tianhy.springbootredis", annotationClass = Repository.class)
//加入驱动缓存
@EnableCaching
public class SpringbootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisApplication.class, args);
    }


    //RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate = null;

    //Redis 连接工厂
    @Autowired
    private RedisConnectionFactory redisConnectionFactory = null;

    //Redis消息监听器
    @Autowired
    private MessageListener messageListener = null;

    //任务池
    private ThreadPoolTaskScheduler taskScheduler = null;

    //自定义初始化方法
    @PostConstruct
    public void initRedis() {
        initRdisTemplate();
    }

    //设置序列化器
    public void initRdisTemplate() {
        RedisSerializer serializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
    }


    //创建任务池，运行线程等待处理Redis的消息
    @Bean(name = "initThreadPoolTaskScheduler")
    public ThreadPoolTaskScheduler initThreadPoolTaskScheduler() {
        if (taskScheduler != null) {
            return this.taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    //消息监听容器
    @Bean(name = "initRedisMessageListenerContainer")
    public RedisMessageListenerContainer initRedisMessageListenerContainer() {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //设置连接工厂
        container.setConnectionFactory(redisConnectionFactory);
        //设置运行任务池
        container.setTaskExecutor(initThreadPoolTaskScheduler());
        //定义监听渠道
        Topic topic = new ChannelTopic("channel");
        //监听器监听Redis消息
        container.addMessageListener(messageListener, topic);
        return container;
    }

    //自定义缓存管理器
    public RedisCacheManager initRedisCacheManager() {
        //Redis加锁的写入器
        RedisCacheWriter cacheWriter = RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory);
        //Redis缓存默认配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        //JDK序列化器
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new JdkSerializationRedisSerializer()));
        //禁用前缀
        config = config.disableKeyPrefix();
        config = config.entryTtl(Duration.ofMinutes(10));
        //创建Redis缓存管理器
        RedisCacheManager redisCacheManager = new RedisCacheManager(cacheWriter, config);
        return redisCacheManager;
    }


}
