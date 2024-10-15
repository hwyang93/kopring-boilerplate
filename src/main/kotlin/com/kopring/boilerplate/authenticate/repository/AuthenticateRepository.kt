package com.kopring.boilerplate.authenticate.repository

import com.kopring.boilerplate.authenticate.entities.LoginToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthenticateRepository : JpaRepository<LoginToken, Long>, AuthenticateRepositoryQuerydsl