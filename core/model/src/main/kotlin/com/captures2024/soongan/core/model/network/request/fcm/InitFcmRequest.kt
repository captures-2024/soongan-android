package com.captures2024.soongan.core.model.network.request.fcm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InitFcmRequest(
    @SerialName("token")
    val token: String,
    @SerialName("deviceId")
    val deviceId: String,
)
