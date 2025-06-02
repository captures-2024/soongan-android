package com.captures2024.soongan.data.source.notification.impl.mapper

import com.captures2024.soongan.core.model.dto.NotificationCountDto
import com.captures2024.soongan.core.model.network.response.notifications.NotificationCountResponse
import com.captures2024.soongan.core.model.utils.NotificationType

internal fun NotificationCountResponse.toNotificationCountItem(): NotificationCountDto = NotificationCountDto(
    count = count,
    type = NotificationType.valueOf(type),
)
