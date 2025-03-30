package com.captures2024.soongan.core.model.dto

import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.model.utils.NotificationType

data class NotificationsDto(
    val type: NotificationType = NotificationType.CONTEST,
    val notifications: List<Notification> = emptyList(),
)

data class Notification(
    val id: Long = -1,
    val title: String = "",
    val body: String = "",
    val subType: NotificationSubType = NotificationSubType.CONTEST_START,
    val isRead: Boolean = false,
    val redirectUrl: String? = null,
    val createdAt: String = "",
)