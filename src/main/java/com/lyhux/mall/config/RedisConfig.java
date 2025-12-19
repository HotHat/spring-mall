package com.lyhux.mall.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Configuration
public class RedisConfig {

    // @Bean
    // JedisConnectionFactory jedisConnectionFactory() {
    //    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
    //    config.setDatabase(1);
    //    // JedisConnectionFactory factory = new JedisConnectionFactory(config);
    //    return new JedisConnectionFactory(config);
    //     // return new JedisConnectionFactory();
    // }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // 1. Key Serializer: Use Strings (so they are readable in redis-cli)
        template.setKeySerializer(new StringRedisSerializer());

        // 2. Value Serializer: Use JSON for records/objects
        ObjectMapper mapper = new ObjectMapper();

        // Register modules for modern Java features (Records, Optional, JavaTime)
        mapper.registerModule(new JavaTimeModule());

        // IMPORTANT: Tell Jackson to include type information
        // This allows GenericJackson2JsonRedisSerializer to know which Record class to use
        mapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(mapper);
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
