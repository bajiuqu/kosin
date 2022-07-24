package com.bajiuqu.dict.common.entity.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * Redis 配置类
 * 1: 序列化保存方式
 *
 * @author 王瑞敏
 * @Description: SpringBoot 中的 Redis 默认使用的是 JdkSerializationRedisSerializer 序列化方式，
 * 存储之后会将中文转换成十六进制数据，查看不方便，所以选择使用了 Jackson2JsonRedisSerializer 序列化方式进行存储
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.database}")
    private int database;

    /**
     * Jackson2JsonRedisSerializer 序列化方式，序列化时<不会>保存目标对象的包名和类名
     * 序列化时如果是带有泛型的对象，会以 Map 形式存储，反序列化时 Map 转换泛型类型 就会失败！
     * 解决方案：序列化保存时，转换成 JSON 字符串
     * 存储示例数据:
     * [
     * {
     * "dictCode":"20",
     * "pdictCode":"0",
     * "groupId":"7708",
     * "dictName":"鍥戒骇",
     * "id":9334
     * }
     * ]
     *
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        factory.setDatabase(database);
        template.setConnectionFactory(factory);
        // 定义Jackson2JsonRedisSerializer序列化对象
        Jackson2JsonRedisSerializer<Object> jacksonSeial = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会报异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);
        StringRedisSerializer stringSerial = new StringRedisSerializer();
        // redis key 序列化方式使用stringSerial
        template.setKeySerializer(stringSerial);
        // redis value 序列化方式使用jackson
        template.setValueSerializer(jacksonSeial);
        // redis hash key 序列化方式使用stringSerial
        template.setHashKeySerializer(stringSerial);
        // redis hash value 序列化方式使用jackson
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * GenericJackson2JsonRedisSerializer 序列化时<会>保存目标对象的包名和类名
     * 反序列化时，如果时带有泛型的数据不会报错，
     * 存储示例数据:
     * [
     *     {
     *         "@class":"com.alibaba.fastjson.JSONObject",
     *         "dictCode":"20",
     *         "pdictCode":"0",
     *         "groupId":"7708",
     *         "dictName":"鍥戒骇",
     *         "id":9334
     *     }
     * ]
     * @param connectionFactory
     * @return
     */
//    @Bean
//    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
//        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
//        // 序列化 key 的方式
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        // 序列化 value 的方式
//        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
//
//        redisTemplate.setKeySerializer(stringRedisSerializer);
//        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
//        redisTemplate.setHashKeySerializer(stringRedisSerializer);
//        redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
//
//        // Sets the index of the database used by this connection factory.
//        connectionFactory.setDatabase(database);
//        redisTemplate.setConnectionFactory(connectionFactory);
//        return redisTemplate;
//    }

}
