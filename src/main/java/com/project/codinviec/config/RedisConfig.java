package com.project.codinviec.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfig {
    private String host;

    private String password;

    private int port;

//    db0 lưu thông tin block user
//    db01 accesstoken refreshtoken
//    db02 cache location

    // DB 0
    @Bean(name = "redisConnectionFactoryDb0")
    @Primary
    public LettuceConnectionFactory redisConnectionFactoryDb0() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setDatabase(0);
        return factory;
    }

    // DB 1
    @Bean(name = "redisConnectionFactoryDb1")
    public LettuceConnectionFactory redisConnectionFactoryDb1() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setDatabase(1);
        return factory;
    }

    // 👉 DB 2
    @Bean(name = "redisConnectionFactoryDb2")
    public LettuceConnectionFactory redisConnectionFactoryDb2() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setDatabase(2);
        return factory;
    }

    // RedisTemplate cho DB0
    @Bean(name = "redisTemplateDb0")
    @Primary
    public RedisTemplate<String, String> redisTemplateDb0() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactoryDb0());

        template.setValueSerializer(new StringRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        return template;
    }

    // RedisTemplate cho DB1
    @Bean(name = "redisTemplateDb1")
    public RedisTemplate<String, String> redisTemplateDb1() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactoryDb1());

        template.setValueSerializer(new StringRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        return template;
    }

    // RedisTemplate cho DB2
    @Bean(name = "redisTemplateDb2")
    public RedisTemplate<String, String> redisTemplateDb2() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactoryDb2());
        template.setValueSerializer(new StringRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        return template;
    }
}