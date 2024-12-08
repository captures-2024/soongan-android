package com.captures2024.soongan.core.model.network.response.members

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMemberInfoResponse(
    @SerialName("email")
    val email: String,
    @SerialName("nickname")
    val nickname: String? = null,
    @SerialName("birthYear")
    val birthYear: Int? = null,
    @SerialName("profileImageUrl")
    val profileImageUrl: String? = null,
)