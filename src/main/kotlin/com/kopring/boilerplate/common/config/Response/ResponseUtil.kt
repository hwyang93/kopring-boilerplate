package com.kopring.boilerplate.common.config.Response

import org.springframework.http.ResponseEntity

object ResponseUtil {
    fun simpleResponse(status: String = "Success", message: String = "요청이 성공했습니다."): ResponseEntity<Map<String, String>> {
        return ResponseEntity.ok(mapOf("status" to status, "message" to message))
    }

    fun <T> serializeResponse(data: T): ResponseEntity<T> {
        return ResponseEntity.ok(data)
    }

}