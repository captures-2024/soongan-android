package com.captures2024.soongan.core.model.network.response.notifications


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias GetNotificationsCountResponse = List<NotificationCountResponse>

@Serializable
data class NotificationCountResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("type")
    val type: String
)