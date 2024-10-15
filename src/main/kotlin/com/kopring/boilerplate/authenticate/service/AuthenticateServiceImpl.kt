package com.kopring.boilerplate.authenticate.service

import com.kopring.boilerplate.authenticate.entities.LoginToken
import com.kopring.boilerplate.authenticate.repository.AuthenticateRepositoryImpl
import com.kopring.boilerplate.common.config.db.CompositeTransactionManager
import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.DefaultTransactionDefinition

@Service
class AuthenticateServiceImpl (private val authenticateRepository: AuthenticateRepositoryImpl,
                               private val compositeTransactionManager: CompositeTransactionManager, private val firstDBTransactionManager: PlatformTransactionManager
) : AuthenticateService {

    override fun getLoginTokenByAccessToken(accessToken: String): LoginToken? {
        val status = compositeTransactionManager.getTransaction(DefaultTransactionDefinition())
        try {
            compositeTransactionManager.commit(status)
        } catch (e: Exception) {
            compositeTransactionManager.rollback(status)
        }
        return authenticateRepository.findByToken(accessToken)
    }

}