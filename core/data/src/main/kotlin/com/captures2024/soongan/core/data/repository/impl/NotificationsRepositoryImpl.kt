package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.data.remote.impl.NotificationsDataSourceImpl
import com.captures2024.soongan.core.data.repository.NotificationsRepository
import com.captures2024.soongan.core.model.dto.NotificationsCountDto
import com.captures2024.soongan.core.model.dto.NotificationsDto
import com.captures2024.soongan.core.model.utils.NotificationType
import javax.inject.Inject

class NotificationsRepositoryImpl
@Inject
constructor(
    private val dataSourceImpl: NotificationsDataSourceImpl,
) : NotificationsRepository {

    override suspend fun getNotifications(type: NotificationType): NotificationsDto {
        val notifications = dataSourceImpl.getNotifications(type = type)

        return notifications ?: throw java.lang.NullPointerException("notificationsDto is null")
    }

    override suspend fun getNotificationsCount(): NotificationsCountDto {
        val notificationsCount = dataSourceImpl.getNotificationsCount()

        return notificationsCount
            ?: throw java.lang.NullPointerException("notificationsCountDto is null")
    }

    override suspend fun postNotificationRead(notificationId: Long): Boolean =
        dataSourceImpl.postNotificationRead(notificationId = notificationId)

    override suspend fun deleteNotification(notificationId: Long): Boolean =
        dataSourceImpl.deleteNotification(notificationId = notificationId)
}
