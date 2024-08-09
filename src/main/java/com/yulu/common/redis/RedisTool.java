package com.yulu.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
@Component
public class RedisTool {
    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 根据key获取redis缓存值
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getRedisCache(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 根据key删除redis缓存
     * @param key
     * @return
     */
    public boolean deleteRedisCache(final String key) {
        return redisTemplate.delete(key);
    }


    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的key
     * @param value 缓存的值
     * @param timeout 多久过期
     * @param timeUnit 时间颗粒度
     */
    public <T> void setRedisCache(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }
}
