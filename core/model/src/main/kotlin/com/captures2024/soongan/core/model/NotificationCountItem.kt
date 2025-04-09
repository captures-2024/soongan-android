package com.captures2024.soongan.core.model

import com.captures2024.soongan.core.model.utils.NotificationType

data class NotificationCountItem(
    val count: Int = 0,
    val type: NotificationType = NotificationType.CONTEST,
)
