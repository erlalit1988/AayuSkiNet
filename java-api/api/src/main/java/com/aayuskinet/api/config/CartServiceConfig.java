package com.aayuskinet.api.config;

import com.aayuskinet.core.interfaces.ICartService;
import com.aayuskinet.infrastructure.CartServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class CartServiceConfig {
    @Bean
    public ICartService cartService(StringRedisTemplate redisTemplate) {
        return new CartServiceImpl(redisTemplate);
    }
}
