package com.captures2024.soongan.core.data.repository

import com.captures2024.soongan.core.model.dto.NotificationsCountDto
import com.captures2024.soongan.core.model.dto.NotificationsDto
import com.captures2024.soongan.core.model.utils.NotificationType

interface NotificationsRepository {

    suspend fun getNotifications(
        type: NotificationType
    ): NotificationsDto

    suspend fun getNotificationsCount(): NotificationsCountDto

    suspend fun postNotificationRead(
        notificationId: Long
    ): Boolean

    suspend fun deleteNotification(
        notificationId: Long
    ): Boolean
}
