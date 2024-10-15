package com.kopring.boilerplate.members.controller

import com.kopring.boilerplate.common.config.Response.ResponseUtil
import com.kopring.boilerplate.members.entities.Member
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
@Tag(name = "회원 관리", description = "회원 관련 API")
class MemberController {

    @GetMapping("/simple_response")
    @Operation(summary = "simple response", description = "simple response test")
    fun responseTest(): ResponseEntity<Map<String, String>> {
        return ResponseUtil.simpleResponse()

    }

    @GetMapping("/simple_response_serial")
    @Operation(summary = "simple response", description = "simple response test")
    fun serialResponseTest(): ResponseEntity<Any> {
        return ResponseUtil.serializeResponse(mapOf("a" to "a", "b" to "b"))

    }

    @GetMapping("/me")
    @Operation(summary = "내 정보 조회", description = "내 정보 조회")
    fun getMember(@RequestAttribute("member") member: Member): ResponseEntity<Any> {
        val result = {
            "id" to member.id
            "uuid" to member.uuid
        }
        return ResponseUtil.serializeResponse(result)
    }


}