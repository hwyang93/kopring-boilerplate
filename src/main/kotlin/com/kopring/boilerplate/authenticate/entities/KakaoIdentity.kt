package com.kopring.boilerplate.authenticate.entities

import com.kopring.boilerplate.common.entities.BaseTimeEntityWithDeletedAt
import com.kopring.boilerplate.members.entities.Member
import jakarta.persistence.*

@Entity
@Table(
    name = "kakao_identities", schema = "first", indexes = [
        Index(name = "kakao_identities_user_id_2b27300a", columnList = "user_id"),
        Index(name = "kakao_ident_member__54cbac_idx", columnList = "member_id, deleted_at")
    ]
)
class KakaoIdentity (
    @Id
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "user_id", nullable = false)
    var userId: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member? = null
) : BaseTimeEntityWithDeletedAt() {


}