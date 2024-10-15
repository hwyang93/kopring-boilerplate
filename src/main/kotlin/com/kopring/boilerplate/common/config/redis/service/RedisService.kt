package com.kopring.boilerplate.common.config.redis.service

import org.springframework.stereotype.Service
import java.time.Duration

@Service
interface RedisService {
    fun setValues(key: String, value: String)

    fun setValues(key: String, value: String, duration: Duration)

    fun getValue(key: String): String?

    fun deleteValue(key: String): Boolean
}