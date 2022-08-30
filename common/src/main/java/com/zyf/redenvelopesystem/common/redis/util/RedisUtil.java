package com.zyf.redenvelopesystem.common.redis.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    protected final RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     *
     * @param expireTime 单位为秒
     */
    public Boolean setNx(String key, Object value,long expireTime){
        return redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, TimeUnit.SECONDS);
    }
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
    public Boolean deleteKey(String key){
        return redisTemplate.delete(key);
    }
    public void set(String key,Object object){
        redisTemplate.opsForValue().set(key,object);
    }
}
