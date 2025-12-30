package com.captures2024.soongan.core.model.dto

import com.captures2024.soongan.core.model.utils.NotificationType

data class NotificationCountDto(
    val count: Int = 0,
    val type: NotificationType = NotificationType.CONTEST,
)
