package com.zyf.redenvelopesystem.async.config.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient getClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.233.200:6379");
        config.useSingleServer().setPassword("3333");
        return Redisson.create(config);
    }
}
