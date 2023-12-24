package com.cyberglint.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CyberGlintRedissonConfig {
    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private String port;
    @Value("${spring.data.redis.password}")
    private String password;
    
    /**
     * 配置一个临时的对象到spring容器中，不使用
     *
     * @return 一个RedissonClient的实现
     */
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 使用单节点配置
        config.useSingleServer().setAddress("redis://" + host + ":" + port)
                .setPassword(password);
        return Redisson.create(config);
    }
}
