package com.captures2024.soongan.data.source.notification.impl.mapper

import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto
import com.captures2024.soongan.core.model.network.response.notifications.GetNotificationsCountResponse

internal fun GetNotificationsCountResponse.toNotificationsCountDto(): NotificationsCountInfoDto = NotificationsCountInfoDto(
    notificationCountItems = this.map { it.toNotificationCountItem() },
)
