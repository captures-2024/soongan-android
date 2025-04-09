package com.captures2024.soongan.core.data.remote.impl

import com.captures2024.soongan.core.data.mapper.toNotificationsCountDto
import com.captures2024.soongan.core.data.mapper.toNotificationsDto
import com.captures2024.soongan.core.data.remote.NotificationsDataSource
import com.captures2024.soongan.core.data.service.NotificationsService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.model.dto.NotificationsCountDto
import com.captures2024.soongan.core.model.dto.NotificationsDto
import com.captures2024.soongan.core.model.utils.NotificationType
import javax.inject.Inject

class NotificationsDataSourceImpl
@Inject
constructor(
    private val service: NotificationsService,
) : NotificationsDataSource {

    override suspend fun getNotifications(type: NotificationType): NotificationsDto? = safeAPICall {
        service.getNotifications(type = type.name)
    }.body?.responseData?.toNotificationsDto()

    override suspend fun getNotificationsCount(): NotificationsCountDto? = safeAPICall {
        service.getNotificationsCount()
    }.body?.responseData?.toNotificationsCountDto()

    override suspend fun postNotificationRead(notificationId: Long): Boolean {
        val result = safeAPICall {
            service.postNotificationRead(notificationId = notificationId)
        }.body

        return when (result) {
            null -> false
            else -> true
        }
    }

    override suspend fun deleteNotification(notificationId: Long): Boolean {
        val result = safeAPICall {
            service.deleteNotification(notificationId = notificationId)
        }.body

        return when (result) {
            null -> false
            else -> true
        }
    }
}
