package com.kopring.boilerplate.common.config.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.MaxUploadSizeExceededException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CustomException::class)
    fun handleHavitException(ex: CustomException): ResponseEntity<Map<String, Any?>> {
//        return ResponseEntity(mapOf("a" to ex.message, "b" to HttpStatus.HttpStatus ), HttpStatus.BAD_REQUEST)
        return ex.response()
    }

    @ExceptionHandler(MaxUploadSizeExceededException::class)
    fun handleMaxSizeException(ex: MaxUploadSizeExceededException): ResponseEntity<Map<String, Any?>> {
        return CustomException(413, "REQUEST_DATA_TOO_BIG", ex.message).response()
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<Map<String, Any?>> {
        return InternalServerErrorException.response()
    }
}