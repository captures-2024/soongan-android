package com.captures2024.soongan.core.data.repository

import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto
import com.captures2024.soongan.core.model.dto.NotificationsInfoDto
import com.captures2024.soongan.core.model.utils.NotificationType
import kotlinx.coroutines.flow.Flow

interface NotificationsRepository {
    val notificationEvent: Flow<NotificationDto?>

    fun emitNotification(payload: Map<String, Any?>)

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
