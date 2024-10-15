package com.kopring.boilerplate.authenticate.repository

import com.kopring.boilerplate.authenticate.entities.LoginToken

interface AuthenticateRepositoryQuerydsl {
        fun findByToken(accessToken: String): LoginToken?
}