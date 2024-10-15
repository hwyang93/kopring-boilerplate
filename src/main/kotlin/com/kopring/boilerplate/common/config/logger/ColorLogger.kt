package com.kopring.boilerplate.common.config.logger


import org.slf4j.event.Level
import org.springframework.boot.ansi.AnsiColor
import org.springframework.boot.ansi.AnsiOutput
import org.springframework.stereotype.Component

@Component
class ColorLogger(private val logger: Logger) {
    fun log(level: Level, msg: String, loggingContext: Map<String, Any?>) {
        val coloredMsg = when {
            level.toInt() >= Level.ERROR.toInt() -> AnsiOutput.toString(AnsiColor.MAGENTA, msg)
            else -> AnsiOutput.toString(AnsiColor.CYAN, msg)
        }
        logger.log(level, coloredMsg, loggingContext)
    }

    fun logError(level: Level, msg: String, loggingContext: Map<String, Any?>) {
        val coloredMsg = AnsiOutput.toString(AnsiColor.MAGENTA, msg)
        logger.logError(level, coloredMsg, loggingContext)
    }
}