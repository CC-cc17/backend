package com.skyblue.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class MyRedisConfig {
    private final RedisConnectionFactory factory;

    public MyRedisConfig(RedisConnectionFactory factory) {
        this.factory = factory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // 创建并定制 JavaTimeModule
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        // 定义 LocalDateTime 的序列化方式
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // 定制 ObjectMapper 来序列化值
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // 禁止将日期序列化为时间戳
        objectMapper.registerModule(javaTimeModule); // 注册自定义的 JavaTimeModule

        // 使用 StringRedisSerializer 来序列化键
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        // 使用定制的 ObjectMapper 创建 GenericJackson2JsonRedisSerializer
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        redisTemplate.setValueSerializer(serializer);

        return redisTemplate;
    }
}