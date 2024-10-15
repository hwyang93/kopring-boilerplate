package com.kopring.boilerplate.authenticate.repository

import com.kopring.boilerplate.authenticate.entities.LoginToken
import com.kopring.boilerplate.authenticate.entities.QLoginToken
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class AuthenticateRepositoryImpl (private val queryFactory: JPAQueryFactory) : AuthenticateRepositoryQuerydsl {

    private val qLoginToken = QLoginToken.loginToken

    override fun findByToken(accessToken: String): LoginToken? {
        return queryFactory.selectFrom(qLoginToken)
            .where(qLoginToken.accessToken.eq(accessToken))
            .fetchOne()
    }

}