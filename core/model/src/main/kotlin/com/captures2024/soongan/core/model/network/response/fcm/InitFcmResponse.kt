package com.captures2024.soongan.core.model.network.response.fcm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InitFcmResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("token")
    val token: String,
    @SerialName("deviceId")
    val deviceId: String,
    @SerialName("deviceType")
    val deviceType: String
)