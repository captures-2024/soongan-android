package com.captures2024.soongan.core.model.network.response.members

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInWithTokenResponse(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String
)
