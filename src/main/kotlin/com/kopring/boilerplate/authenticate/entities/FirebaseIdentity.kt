package com.kopring.boilerplate.authenticate.entities

import com.kopring.boilerplate.common.entities.BaseTimeEntityWithDeletedAt
import com.kopring.boilerplate.members.entities.Member
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(
    name = "firebase_identities", schema = "havit", indexes = [
        Index(name = "firebase_identities_uid_94c6b3bf", columnList = "uid"),
        Index(name = "firebase_id_member__54d3d3_idx", columnList = "member_id, deleted_at")
    ]
)
class FirebaseIdentity (
    @Id
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "uid", nullable = false, length = 128)
    var uid: String? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member? = null
) : BaseTimeEntityWithDeletedAt() {
}