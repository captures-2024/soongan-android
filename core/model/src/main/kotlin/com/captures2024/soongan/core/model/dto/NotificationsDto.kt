package com.captures2024.soongan.core.model.dto

import com.captures2024.soongan.core.model.Notification
import com.captures2024.soongan.core.model.utils.NotificationType

data class NotificationsDto(
    val type: NotificationType = NotificationType.CONTEST,
    val notifications: List<Notification> = emptyList(),
)
