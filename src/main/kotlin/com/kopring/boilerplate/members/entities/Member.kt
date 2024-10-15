package com.kopring.boilerplate.members.entities

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(
    name = "members", schema = "first", indexes = [
        Index(name = "members_uuid_5a8dae_idx", columnList = "uuid"),
        Index(name = "members_device_id_d01535db", columnList = "device_id")
    ], uniqueConstraints = [
        UniqueConstraint(name = "uuid", columnNames = ["uuid"])
    ]
)
open class Member {
    @Id
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "password", nullable = false, length = 128)
    open var password: String? = null

    @Column(name = "last_login")
    open var lastLogin: Instant? = null

    @Column(name = "uuid", nullable = false, length = 64)
    open var uuid: String? = null

    @Column(name = "nickname", nullable = false, length = 100)
    open var nickname: String? = null

    @Column(name = "login_type", nullable = false, length = 10)
    open var loginType: String? = null

    @Column(name = "is_staff", nullable = false)
    open var isStaff: Boolean? = false

    @Column(name = "is_superuser", nullable = false)
    open var isSuperuser: Boolean? = false

    @Column(name = "is_deleted", nullable = false)
    open var isDeleted: Boolean? = false

    @Column(name = "device_id")
    open var deviceId: String? = null

    @Column(name = "last_logout")
    open var lastLogout: Instant? = null

    @Column(name = "sendbird_user", nullable = false)
    open var sendbirdUser: Boolean? = false

    @Column(name = "sendbird_push", nullable = false)
    open var sendbirdPush: Boolean? = false

    @Column(name = "country", length = 10)
    open var country: String? = null

    @Column(name = "is_tester", nullable = false)
    open var isTester: Boolean? = false

    @Column(name = "language", length = 10)
    open var language: String? = null

    @Column(name = "latitude")
    open var latitude: Double? = null

    @Column(name = "longitude")
    open var longitude: Double? = null

    @Column(name = "created_at")
    open var createdAt: Instant? = null
}