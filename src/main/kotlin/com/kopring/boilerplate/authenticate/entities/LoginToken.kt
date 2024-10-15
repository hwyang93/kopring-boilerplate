package com.kopring.boilerplate.authenticate.entities

import com.kopring.boilerplate.common.entities.BaseTimeEntityWithDeletedAt
import com.kopring.boilerplate.members.entities.Member
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(
    name = "login_tokens", schema = "first", indexes = [
        Index(name = "login_tokens_access_token_afd29965", columnList = "access_token"),
        Index(name = "login_tokens_refresh_token_e79fbddb", columnList = "refresh_token")
    ]
)
class LoginToken (
    @Id
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "access_token", nullable = false, length = 256)
    var accessToken: String? = null,

    @Column(name = "refresh_token", nullable = false, length = 256)
    var refreshToken: String? = null,

    @Column(name = "expires_in", nullable = false)
    var expiresIn: Int = 0,

    @Column(name = "refresh_token_expires_in", nullable = false)
    var refreshTokenExpiresIn: Int? = null,

    @Column(name = "secret_key", nullable = false, length = 256)
    var secretKey: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member? = null,

) : BaseTimeEntityWithDeletedAt() {
    fun isExpired(): Boolean {
        return deletedAt != null || createdAt.epochSecond + expiresIn < Instant.now().epochSecond
    }

    override fun destroy() {
        super.destroy()
    }
}