package com.bajiuqu.manage.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 五种常用序列化方式
     * Jackson2JsonRedisSerializer 序列化对象为 json 字符串
     * GenericJackson2JsonRedisSerializer 与 Jackson2JsonRedisSerializer 类似，构造对象时不需要传入特定的类，去参考其序列化方式
     *
     * StringRedisSerializer 简单的字符串序列化 --- StringRedisTemplate client 就是这种序列化方式
     * GenericToStringSerializer 可以将任何对象泛化为字符串并序列化
     *
     * JdkSerializationRedisSerializer 默认的序列化策略
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置默认的序列化方式
        redisTemplate.setDefaultSerializer(genericJackson2JsonRedisSerializer);
        // 普通 key
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // 普通 value
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        // hash key
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // hash value
        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        // 开启默认序列化方式
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

}
