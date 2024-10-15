package com.kopring.boilerplate.common.config.redis.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Service
import java.time.Duration


@Service
class RedisServiceImpl(private val redisTemplate: RedisTemplate<String, Any>) : RedisService {

    override fun setValues(key: String, value: String) {
        val values: ValueOperations<String, Any> = redisTemplate.opsForValue()
        values.set(key, value)
    }

    override fun setValues(key: String, value: String, duration: Duration) {
        val values: ValueOperations<String, Any> = redisTemplate.opsForValue()
        values.set(key, value, duration)
    }


    override fun getValue(key: String): String? {
        val values: ValueOperations<String, Any> = redisTemplate.opsForValue()
        return values.get(key)?.toString() ?: ""
    }


    override fun deleteValue(key: String): Boolean {
        return redisTemplate.delete(key)
    }
}