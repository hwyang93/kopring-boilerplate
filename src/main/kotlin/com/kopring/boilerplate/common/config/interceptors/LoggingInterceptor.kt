package com.kopring.boilerplate.common.config.interceptors

import com.kopring.boilerplate.common.config.annotaions.NoLogging
import com.kopring.boilerplate.common.config.logger.ColorLogger
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

import org.slf4j.event.Level
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.util.ContentCachingRequestWrapper


@Component
class LoggingInterceptor(
    private val colorLogger: ColorLogger,
    private val objectMapper: ObjectMapper
) : HandlerInterceptor {

    @Value("\${logging.request.max-body-length:50000}")
    private val maxBodyLength: Int = 50000

    @Value("\${logging.request.sensitive-headers:HTTP_AUTHORIZATION,HTTP_PROXY_AUTHORIZATION}")
    private val sensitiveHeaders: List<String> = listOf()

    @Value("\${logging.request.log-level:INFO}")
    private val logLevel: Level = Level.INFO

    @Value("\${logging.request.http-4xx-log-level:ERROR}")
    private val http4xxLogLevel: Level = Level.ERROR

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler is HandlerMethod) {
            val noLogging = handler.method.getAnnotation(NoLogging::class.java)
            if (noLogging != null) {
                val reason = noLogging.value
                colorLogger.log(Level.INFO, "${request.method} ${request.requestURI} (not logged because '$reason')", emptyMap())
                return true
            }
        }

        val wrappedRequest = ContentCachingRequestWrapper(request)

        return true
    }


}