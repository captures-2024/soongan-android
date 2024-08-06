package com.captures2024.soongan.core.model.network.response.members

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterNicknameResponse(
    @SerialName("memberEmail")
    val id: String,
    @SerialName("updatedNickname")
    val nickname: String
)
