package com.captures2024.soongan.core.data.repository

import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto
import com.captures2024.soongan.core.model.dto.NotificationsInfoDto
import com.captures2024.soongan.core.model.utils.NotificationType

interface NotificationsRepository {

    suspend fun getNotifications(
        type: NotificationType,
    ): NotificationsInfoDto

    suspend fun getNotificationsCount(): NotificationsCountInfoDto

    suspend fun postNotificationRead(
        notificationId: Long,
    ): Boolean

    suspend fun deleteNotification(
        notificationId: Long,
    ): Boolean
}
