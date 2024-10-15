package com.kopring.boilerplate.common.config.logger

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class Logger {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun log(level: org.slf4j.event.Level, msg: String, loggingContext: Map<String, Any?>) {
        when (level) {
            org.slf4j.event.Level.INFO -> logger.info(msg, loggingContext)
            org.slf4j.event.Level.ERROR -> logger.error(msg, loggingContext)
            org.slf4j.event.Level.DEBUG -> logger.debug(msg, loggingContext)
            org.slf4j.event.Level.WARN -> logger.warn(msg, loggingContext)
            org.slf4j.event.Level.TRACE -> logger.trace(msg, loggingContext)
        }
    }

    fun logError(level: org.slf4j.event.Level, msg: String, loggingContext: Map<String, Any?>) {
        log(level, msg, loggingContext)
    }
}