package com.kopring.boilerplate.common.entities

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.time.Instant

@MappedSuperclass
abstract class BaseTimeEntityWithDeletedAt : BaseTimeEntity() {

    @Column(name = "deleted_at", nullable = true)
    var deletedAt: Instant? = null

    open fun destroy() {
        this.deletedAt = Instant.now()
    }
}