package com.captures2024.soongan.data.source.notification.remote

import com.captures2024.soongan.core.model.dto.NotificationSettingDto
import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto
import com.captures2024.soongan.core.model.dto.NotificationsInfoDto
import com.captures2024.soongan.core.model.utils.NotificationType

interface NotificationsRemoteDataSource {

    suspend fun getNotifications(
        type: NotificationType,
    ): NotificationsInfoDto?

    suspend fun getUnreadNotificationsCount(): NotificationsCountInfoDto?

    suspend fun postNotificationRead(
        notificationId: Long,
    ): Boolean

    suspend fun deleteNotification(
        notificationId: Long,
    ): Boolean

    suspend fun getNotificationSettings(): NotificationSettingDto?

    suspend fun patchNotificationSettings(settings: NotificationSettingDto): NotificationSettingDto?
}
