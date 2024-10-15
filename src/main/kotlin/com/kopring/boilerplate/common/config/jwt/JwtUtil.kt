package com.kopring.boilerplate.common.config.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component

@Component
class JwtUtil {
//    private var key: Key? = null
//
//    init {
//        val keyBytes = Decoders.BASE64.decode(secretKey)
//        this.key = Keys.hmacShaKeyFor(keyBytes)
//    }

    fun getUnverifiedClaims(token: String): Claims {
        return Jwts.parserBuilder().build().parseClaimsJwt(token.substringBeforeLast('.') + ".").body
    }

    fun decodeToken(token: String, secretKey: String) {
        Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
            .build()
            .parseClaimsJws(token)
    }
}