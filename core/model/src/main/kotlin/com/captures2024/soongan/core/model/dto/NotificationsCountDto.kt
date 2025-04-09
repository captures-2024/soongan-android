package com.captures2024.soongan.core.model.dto

import com.captures2024.soongan.core.model.NotificationCountItem

data class NotificationsCountDto(
    val notificationCountItems: List<NotificationCountItem> = emptyList(),
)
