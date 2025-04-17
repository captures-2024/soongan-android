package com.captures2024.soongan.core.data.mapper

import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.core.model.dto.NotificationCountDto
import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto
import com.captures2024.soongan.core.model.dto.NotificationsInfoDto
import com.captures2024.soongan.core.model.network.response.notifications.GetNotificationsCountResponse
import com.captures2024.soongan.core.model.network.response.notifications.GetNotificationsResponse
import com.captures2024.soongan.core.model.network.response.notifications.NotificationCountResponse
import com.captures2024.soongan.core.model.network.response.notifications.NotificationResponse
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.model.utils.NotificationType

fun GetNotificationsResponse.toNotificationsDto(): NotificationsInfoDto =
    NotificationsInfoDto(
        type = NotificationType.valueOf(type),
        notifications = notificationResponses.map { it.toNotification() },
    )

fun GetNotificationsCountResponse.toNotificationsCountDto(): NotificationsCountInfoDto =
    NotificationsCountInfoDto(
        notificationCountItems = this.map { it.toNotificationCountItem() },
    )

private fun NotificationResponse.toNotification(): NotificationDto =
    NotificationDto(
        id = id,
        title = title,
        body = body,
        subType = NotificationSubType.valueOf(subType),
        isRead = isRead,
        redirectUrl = redirectUrl,
        createdAt = createdAt,
    )

private fun NotificationCountResponse.toNotificationCountItem(): NotificationCountDto =
    NotificationCountDto(
        count = count,
        type = NotificationType.valueOf(type),
    )
