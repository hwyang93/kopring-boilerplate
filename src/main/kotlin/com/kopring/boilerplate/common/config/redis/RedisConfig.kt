package com.kopring.boilerplate.common.config.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer



@Configuration
class RedisConfig (
    @Value("\${spring.data.redis.host}") private val host: String,
    @Value("\${spring.data.redis.port}") private val port: Int
) {
    @Bean
    fun redisConnectionFactory() : RedisConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()

        redisTemplate.connectionFactory = redisConnectionFactory()

        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = StringRedisSerializer()

        redisTemplate.hashKeySerializer = StringRedisSerializer()
        redisTemplate.hashValueSerializer = StringRedisSerializer()

        redisTemplate.setDefaultSerializer(StringRedisSerializer())

        return redisTemplate
    }
}