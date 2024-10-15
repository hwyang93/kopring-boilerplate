package com.kopring.boilerplate.authenticate.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@Tag(name = "로그인 및 회원가입")
class AuthenticateController {

}