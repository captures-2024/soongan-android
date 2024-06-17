package com.captures2024.soongan.core.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignWithTokenRequest(
    @SerialName("provider")
    val provider: String,
    @SerialName("idToken")
    val idToken: String,
)