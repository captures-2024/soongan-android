package com.captures2024.soongan.data.source.notification.impl.mapper

import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.core.model.network.response.notifications.NotificationResponse
import com.captures2024.soongan.core.model.utils.NotificationSubType

internal fun NotificationResponse.toNotification(): NotificationDto = NotificationDto(
    id = id,
    title = title,
    body = body,
    subType = NotificationSubType.valueOf(subType),
    isRead = isRead,
    redirectUrl = redirectUrl,
    createdAt = createdAt,
)
