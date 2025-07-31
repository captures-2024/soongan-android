package com.captures2024.soongan.domain.repository.notification

import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.core.model.dto.NotificationSettingDto
import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto
import com.captures2024.soongan.core.model.dto.NotificationsInfoDto
import com.captures2024.soongan.core.model.utils.NotificationType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface NotificationRepository {
    val notificationEvent: Flow<NotificationDto?>

    val isNotReadNotificationCache: StateFlow<Boolean>

    fun emitNotification(payload: Map<String, Any?>)

    suspend fun getNotifications(
        type: NotificationType,
    ): NotificationsInfoDto

    suspend fun getUnreadNotificationsCount(): NotificationsCountInfoDto

    suspend fun postNotificationRead(
        notificationId: Long,
    ): Boolean

    suspend fun deleteNotification(
        notificationId: Long,
    ): Boolean

    suspend fun getNotificationSettings(): NotificationSettingDto

    suspend fun patchNotificationSettings(settings: NotificationSettingDto): NotificationSettingDto
}
