package com.captures2024.soongan.core.data.mapper

import com.captures2024.soongan.core.model.dto.Notification
import com.captures2024.soongan.core.model.dto.NotificationCountItem
import com.captures2024.soongan.core.model.dto.NotificationsCountDto
import com.captures2024.soongan.core.model.dto.NotificationsDto
import com.captures2024.soongan.core.model.network.response.notifications.GetNotificationsCountResponse
import com.captures2024.soongan.core.model.network.response.notifications.GetNotificationsResponse
import com.captures2024.soongan.core.model.network.response.notifications.NotificationCountResponse
import com.captures2024.soongan.core.model.network.response.notifications.NotificationResponse
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.model.utils.NotificationType

fun GetNotificationsResponse.toNotificationsDto(): NotificationsDto =
    NotificationsDto(
        type = NotificationType.valueOf(type),
        notifications = notificationResponses.map { it.toNotification() },
    )

fun GetNotificationsCountResponse.toNotificationsCountDto(): NotificationsCountDto =
    NotificationsCountDto(
        notificationCountItems = this.map { it.toNotificationCountItem() },
    )

private fun NotificationResponse.toNotification(): Notification =
    Notification(
        id = id,
        title = title,
        body = body,
        subType = NotificationSubType.valueOf(subType),
        isRead = isRead,
        redirectUrl = redirectUrl,
        createdAt = createdAt,
    )


private fun NotificationCountResponse.toNotificationCountItem(): NotificationCountItem =
    NotificationCountItem(
        count = count,
        type = NotificationType.valueOf(type),
    )
