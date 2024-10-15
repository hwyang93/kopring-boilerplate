package com.kopring.boilerplate.common.config.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "custom 예외")
open class CustomException(
    val statusCode: Int,
    var errorCode: String,
    override val message: String?,
    val displayType: String? = null
) : RuntimeException(message) {

    companion object {
        const val PREFIX = "Custom:EXCEPTION:"
    }

    init {
        this.errorCode = "$PREFIX$errorCode"
    }

    fun response(): ResponseEntity<Map<String, Any?>> {
        val body = mapOf(
            "error_code" to errorCode,
            "message" to message,
            "display_type" to displayType
        )
        return ResponseEntity.status(statusCode).body(body)
    }
}

class InvalidParameterException(arg: String? = null) :
    CustomException(HttpStatus.BAD_REQUEST.value(), "INVALID_PARAMETER", "입력 파라메터가 잘못되었습니다. $arg")

// 400 Bad Request
val InvalidException = CustomException(HttpStatus.BAD_REQUEST.value(), "INVALID", "잘못된 요청입니다.")

// 401 Unauthorized
class InvalidTokenException(arg: String? = null) :
    CustomException(HttpStatus.UNAUTHORIZED.value(), "INVALID_TOKEN", "토큰이 잘못되었습니다. token=$arg")

val NotAuthorizedException = CustomException(HttpStatus.UNAUTHORIZED.value(), "NOT_AUTHORIZED", "인증되지 않은 사용자입니다.")
val ExpiredTokenException = CustomException(HttpStatus.UNAUTHORIZED.value(), "EXPIRED_TOKEN", "만료된 토큰입니다.")

// 404 Not Found
val MemberNotFoundException = CustomException(HttpStatus.NOT_FOUND.value(), "MEMBER_NOT_FOUND", "해당 회원을 찾을 수 없습니다.")
val IdentityNotFoundException = CustomException(HttpStatus.NOT_FOUND.value(), "IDENTITY_NOT_FOUND", "본인인증 정보를 찾을 수 없습니다.")
val MemberIdentityNotFoundException = CustomException(HttpStatus.NOT_FOUND.value(), "MEMBER_IDENTITY_NOT_FOUND", "회원 정보를 찾을 수 없습니다.")

class DataNotFoundException(arg: String? = null) :
    CustomException(HttpStatus.NOT_FOUND.value(), "NOT_FOUND", "데이터를 찾을 수 없습니다. $arg")

// 500 Internal Server Error
val InternalServerErrorException = CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생하였습니다.")
val UnknownNiceAuthException = CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "UNKNOWN_NICE_AUTH", "인증 중 알 수 없는 오류가 발생하였습니다.")
val UnknownTwilioAuthException = CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "UNKNOWN_TWILIO_AUTH", "인증 중 알 수 없는 오류가 발생하였습니다.")
val UploadFileFailException = CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "UPLOAD_FILE", "파일 업로드 중 오류가 발생하였습니다.")
val LeaveFailException = CustomException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "LEAVE_FAIL", "회원 탈퇴 중 오류가 발생하였습니다.")