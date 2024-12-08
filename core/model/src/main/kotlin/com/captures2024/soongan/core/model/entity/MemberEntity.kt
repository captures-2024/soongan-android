package com.captures2024.soongan.core.model.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberEntity(
    @SerialName("email")
    val email: String,
    @SerialName("nickname")
    val nickname: String? = null,
    @SerialName("birthYear")
    val birthYear: String? = null,
    @SerialName("profileImageUrl")
    val profileImageUrl: String? = null,
)