package com.kopring.boilerplate.common.config.interceptors

import com.kopring.boilerplate.authenticate.service.AuthenticateService
import com.kopring.boilerplate.common.config.exceptions.ExpiredTokenException
import com.kopring.boilerplate.common.config.exceptions.InvalidTokenException
import com.kopring.boilerplate.common.config.exceptions.NotAuthorizedException
import com.kopring.boilerplate.common.config.jwt.JwtUtil
import io.jsonwebtoken.JwtException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.reflect.Member

@Component
class AuthInterceptor(private val jwtUtil: JwtUtil, private val authenticateService: AuthenticateService) : HandlerInterceptor {
    var member: Member? = null
    var ip: String? = null
    var userAgent: String? = null

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val authorizationHeader = request.getHeader("Authorization")
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

        if (authorizationHeader == "mychew") {
            member = null
        }

        if (authorizationHeader == null || !authorizationHeader.contains("Bearer ")) {
            throw NotAuthorizedException
        }

        val token = authorizationHeader.split(' ')[1]

        //TODO("JWT config 추가")
        val decodedToken = jwtUtil.getUnverifiedClaims(token)
        val accessToken = decodedToken["access_token"] as String?
            ?: throw InvalidTokenException(authorizationHeader)

        //TODO("login token 조회 추가")
        val loginToken = authenticateService.getLoginTokenByAccessToken(accessToken)
            ?: throw InvalidTokenException()


        //TODO("만료된 토큰 처리;")
        if (loginToken.isExpired()) {
            throw ExpiredTokenException
        }
        try {
            println("token= $token, secret=${loginToken.secretKey}, member_id=${loginToken.member?.id}")
            jwtUtil.decodeToken(token = token, secretKey = loginToken.secretKey)
            request.setAttribute("member", loginToken.member)
        } catch (e: JwtException) {
            throw InvalidTokenException("${authorizationHeader.split(' ')[1]}, secret=${loginToken}, member_id=${member}")
        }

        return super.preHandle(request, response, handler)
    }
}