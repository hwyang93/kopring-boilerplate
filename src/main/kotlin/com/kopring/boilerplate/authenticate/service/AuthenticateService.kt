package com.kopring.boilerplate.authenticate.service

import com.kopring.boilerplate.authenticate.entities.LoginToken

interface AuthenticateService{

    fun getLoginTokenByAccessToken(accessToken: String): LoginToken?

}