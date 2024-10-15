package com.kopring.boilerplate.common.config.interceptors

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class PublicInterceptor() : HandlerInterceptor  {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val acceptedLanguage = request.getHeader("Accept-Language")
        val (countryCode, languageCode) = if (acceptedLanguage != null) {
            val parts = acceptedLanguage.split("-")
            (parts.getOrNull(1)?.lowercase() ?: "") to parts.getOrNull(0)?.lowercase()
        } else {
            null to null
        }

        val userAgent = mapOf(
            "appVersion" to request.getHeader("App-Version"),
            "osVersion" to request.getHeader("OS-Version"),
            "clientIp" to request.getHeader("Client-IP"),
            "timezone" to request.getHeader("Timezone"),
            "acceptedLanguage" to acceptedLanguage,
            "countryCode" to countryCode,
            "languageCode" to languageCode,
            "geoLocation" to request.getHeader("Geo-Location")
        )

        println(userAgent)

        request.setAttribute("user_agent", userAgent)


        return super.preHandle(request, response, handler)
    }
}