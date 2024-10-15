package com.captures2024.soongan.core.model.network.response.members

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMemberInformationResponse(
    @SerialName("email")
    val email: String,
    @SerialName("nickname")
    val nickname: String?,
    @SerialName("birthDate")
    val birthDate: String?,
    @SerialName("profileImageUrl")
    val profileImage: String?,
)
