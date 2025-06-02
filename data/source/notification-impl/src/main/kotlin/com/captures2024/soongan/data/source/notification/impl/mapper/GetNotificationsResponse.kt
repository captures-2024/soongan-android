package com.captures2024.soongan.data.source.notification.impl.mapper

import com.captures2024.soongan.core.model.dto.NotificationsInfoDto
import com.captures2024.soongan.core.model.network.response.notifications.GetNotificationsResponse
import com.captures2024.soongan.core.model.utils.NotificationType

internal fun GetNotificationsResponse.toNotificationsDto(): NotificationsInfoDto = NotificationsInfoDto(
    type = NotificationType.valueOf(type),
    notifications = notificationResponses.map { it.toNotification() },
)
