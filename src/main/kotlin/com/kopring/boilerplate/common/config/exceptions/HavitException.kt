package com.kopring.boilerplate.common.config.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Havit 예외")
open class HavitException(
    val statusCode: Int,
    var errorCode: String,
    override val message: String?,
    val displayType: String? = null
) : RuntimeException(message) {

    companion object {
        const val PREFIX = "HAVIT:EXCEPTION:"
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

class SendbirdClientErrorException(message: String = "샌드버드 API 호출 중 오류가 발생하였습니다.") :
    HavitException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "SENDBIRD_CLIENT_ERROR", message)

class InvalidParameterException(arg: String? = null) :
    HavitException(HttpStatus.BAD_REQUEST.value(), "INVALID_PARAMETER", "입력 파라메터가 잘못되었습니다. $arg")

// 400 Bad Request
val InvalidException = HavitException(HttpStatus.BAD_REQUEST.value(), "INVALID", "잘못된 요청입니다.")
val InvalidNicknameException = HavitException(HttpStatus.BAD_REQUEST.value(), "INVALID_NICKNAME", "사용할수 없는 단어가 포함되어 있습니다.")
val InvalidOccupationException = HavitException(HttpStatus.BAD_REQUEST.value(), "INVALID_OCCUPATION", "사용할수 없는 단어가 포함되어 있습니다.")
val InvalidIntroductionException = HavitException(HttpStatus.BAD_REQUEST.value(), "INVALID_INTRODUCTION", "사용할수 없는 단어가 포함되어 있습니다.")
val InvalidNiceAuthException = HavitException(HttpStatus.BAD_REQUEST.value(), "INVALID_NICE_AUTH", "인증에 실패하였습니다. 인증정보를 확인해주세요.")
val NoUploadFileException = HavitException(HttpStatus.BAD_REQUEST.value(), "NO_UPLOAD_FILE", "업로드할 파일이 없습니다.")
val InvalidUploadFileException = HavitException(HttpStatus.BAD_REQUEST.value(), "INVALID_UPLOAD_FILE", "업로드 할 수 없는 파일입니다.")
val InvalidRefreshTokenException = HavitException(HttpStatus.BAD_REQUEST.value(), "INVALID_REFRESH_TOKEN", "유효하지 않은 refresh token입니다.")
val ExceedProfilePhotoException = HavitException(HttpStatus.BAD_REQUEST.value(), "EXCEED_PROFILE_PHOTO", "프로필 사진은 최대 6장까지 등록 가능합니다.")
val AlreadyBlockException = HavitException(HttpStatus.BAD_REQUEST.value(), "ALREADY_BLOCK", "이미 차단된 사용자입니다.")
val InvalidProcessOnboardingException = HavitException(HttpStatus.BAD_REQUEST.value(), "INVALID_PROCESS_ONBOARDING", "온보딩 진행 중 오류가 발생하였습니다.")
val JoinAgeLimitException = HavitException(HttpStatus.BAD_REQUEST.value(), "JOIN_AGE_LIMIT", "18세 이상만 가입 가능합니다.")
val PickCountLimitException = HavitException(HttpStatus.BAD_REQUEST.value(), "PICK_COUNT_LIMIT", "하루에 픽할 수 있는 최대치를 초과했습니다.")
val NobodyDetectedException = HavitException(HttpStatus.BAD_REQUEST.value(), "NOBODY_DETECTED", "사진에서 얼굴을 찾을 수 없습니다.")
val TooManyFacesException = HavitException(HttpStatus.BAD_REQUEST.value(), "TOO_MANY_FACES", "사진에 여러 얼굴이 포함되어 있습니다.")
val UnfitPhotoException = HavitException(HttpStatus.BAD_REQUEST.value(), "UNFIT_PHOTO", "사진이 적합하지 않습니다.")
val NotMatchedException = HavitException(HttpStatus.BAD_REQUEST.value(), "NOT_MATCHED", "얼굴이 일치하지 않습니다.")
val AlreadyExistChannelException = HavitException(HttpStatus.BAD_REQUEST.value(), "ALREADY_EXIST_CHANNEL", "이미 채팅방이 존재합니다.")
val AlreadySuperChatException = HavitException(HttpStatus.BAD_REQUEST.value(), "ALREADY_SUPER_CAHT", "이미 슈퍼챗입니다")
val InvalidBirthdayException = HavitException(HttpStatus.BAD_REQUEST.value(), "INVALID_BIRTHDAY", "생년월일 정보가 올바르지 않습니다.")
val InvalidGenderException = HavitException(HttpStatus.BAD_REQUEST.value(), "INVALID_GENDER", "성별 정보가 올바르지 않습니다.")
val NotEnoughCloverException = HavitException(HttpStatus.BAD_REQUEST.value(), "NOT_ENOUGH_CLOVER", "클로버가 부족합니다.")
val NoRestoreItemsException = HavitException(HttpStatus.BAD_REQUEST.value(), "NO_RESTORE_ITEMS", "되돌리기 할 항목이 존재하지 않습니다.")
val NoPickListException = HavitException(HttpStatus.BAD_REQUEST.value(), "NO_PICK_LIST", "픽 리스트가 존재하지 않습니다.")
val NoDiscoveryListException = HavitException(HttpStatus.BAD_REQUEST.value(), "NO_DISCOVERY_LIST", "디스커버리 리스트가 존재하지 않습니다.")

// 401 Unauthorized
class InvalidTokenException(arg: String? = null) :
    HavitException(HttpStatus.UNAUTHORIZED.value(), "INVALID_TOKEN", "토큰이 잘못되었습니다. token=$arg")

val NotAuthorizedException = HavitException(HttpStatus.UNAUTHORIZED.value(), "NOT_AUTHORIZED", "인증되지 않은 사용자입니다.")
val ExpiredTokenException = HavitException(HttpStatus.UNAUTHORIZED.value(), "EXPIRED_TOKEN", "만료된 토큰입니다.")

// 404 Not Found
val MemberNotFoundException = HavitException(HttpStatus.NOT_FOUND.value(), "MEMBER_NOT_FOUND", "해당 회원을 찾을 수 없습니다.")
val IdentityNotFoundException = HavitException(HttpStatus.NOT_FOUND.value(), "IDENTITY_NOT_FOUND", "본인인증 정보를 찾을 수 없습니다.")
val MemberIdentityNotFoundException = HavitException(HttpStatus.NOT_FOUND.value(), "MEMBER_IDENTITY_NOT_FOUND", "회원 정보를 찾을 수 없습니다.")

class DataNotFoundException(arg: String? = null) :
    HavitException(HttpStatus.NOT_FOUND.value(), "NOT_FOUND", "데이터를 찾을 수 없습니다. $arg")

// 500 Internal Server Error
val InternalServerErrorException = HavitException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생하였습니다.")
val UnknownNiceAuthException = HavitException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "UNKNOWN_NICE_AUTH", "인증 중 알 수 없는 오류가 발생하였습니다.")
val UnknownTwilioAuthException = HavitException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "UNKNOWN_TWILIO_AUTH", "인증 중 알 수 없는 오류가 발생하였습니다.")
val UploadFileFailException = HavitException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "UPLOAD_FILE", "파일 업로드 중 오류가 발생하였습니다.")
val LeaveFailException = HavitException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "LEAVE_FAIL", "회원 탈퇴 중 오류가 발생하였습니다.")