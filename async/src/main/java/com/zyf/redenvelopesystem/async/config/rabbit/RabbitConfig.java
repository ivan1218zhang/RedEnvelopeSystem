package com.zyf.redenvelopesystem.async.config.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue GetEnvelopeQueue() {
        // 支持持久化
        return new Queue("GetEnvelopeQueue",true);
    }
}
