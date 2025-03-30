package com.captures2024.soongan.core.model.network.response.notifications


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetNotificationsResponse(
    @SerialName("type")
    val type: String,
    @SerialName("notifications")
    val notificationResponses: List<NotificationResponse>,
)

@Serializable
data class NotificationResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("body")
    val body: String,
    @SerialName("subType")
    val subType: String,
    @SerialName("isRead")
    val isRead: Boolean,
    @SerialName("redirectUrl")
    val redirectUrl: String,
    @SerialName("createdAt")
    val createdAt: String,
)
