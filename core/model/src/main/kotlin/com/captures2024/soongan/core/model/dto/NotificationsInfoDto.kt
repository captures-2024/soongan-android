package com.captures2024.soongan.core.model.dto

import com.captures2024.soongan.core.model.utils.NotificationType

data class NotificationsInfoDto(
    val type: NotificationType = NotificationType.CONTEST,
    val notifications: List<NotificationDto> = emptyList(),
)
