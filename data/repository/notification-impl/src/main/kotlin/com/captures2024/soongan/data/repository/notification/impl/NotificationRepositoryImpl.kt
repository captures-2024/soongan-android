package com.captures2024.soongan.data.repository.notification.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.core.model.dto.NotificationSettingDto
import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto
import com.captures2024.soongan.core.model.dto.NotificationsInfoDto
import com.captures2024.soongan.core.model.dto.fcm.CloudMessage
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.data.source.notification.local.NotificationLocalDataSource
import com.captures2024.soongan.data.source.notification.remote.NotificationsRemoteDataSource
import com.captures2024.soongan.domain.repository.notification.NotificationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val notificationLocalDataSource: NotificationLocalDataSource,
    private val notificationsRemoteDataSource: NotificationsRemoteDataSource,
) : NotificationRepository {
    private val scope by lazy { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    override val notificationEvent: Flow<NotificationDto?>
        get() = notificationLocalDataSource.notificationEvent

    override val cloudMessageEvent: Flow<CloudMessage?>
        get() = notificationLocalDataSource.cloudMessageEvent

    override val isNotReadNotificationCache: StateFlow<Boolean>
        get() = notificationLocalDataSource.isNotReadNotificationCache

    init {
        analyticsHelper.d { "NotificationsRepository:init" }

        scope.launch {
            notificationLocalDataSource.notificationToken.collect {
                notificationLocalDataSource.parseNotification(it)
            }
        }
    }

    override fun emitNotification(payload: Map<String, Any?>) {
        notificationLocalDataSource.emitNotification(payload)
    }

    override suspend fun getNotifications(type: NotificationType): NotificationsInfoDto {
        val notifications = notificationsRemoteDataSource.getNotifications(type = type)

        return notifications ?: throw NullPointerException("notificationsDto is null")
    }

    override suspend fun getUnreadNotificationsCount(): NotificationsCountInfoDto {
        val notificationsCount = notificationsRemoteDataSource.getUnreadNotificationsCount()

        return notificationsCount
            .also {
                notificationLocalDataSource.postIsNotReadNotificationCache(
                    value = it?.notificationCountItems?.isNotEmpty() ?: false,
                )

                it
            }
            ?: throw NullPointerException("notificationsCountDto is null")
    }

    override suspend fun postNotificationRead(notificationId: Long): Boolean {
        analyticsHelper.d { "postNotificationRead - notificationId: $notificationId" }
        return notificationsRemoteDataSource.postNotificationRead(notificationId = notificationId)
    }

    override suspend fun deleteNotification(notificationId: Long): Boolean {
        analyticsHelper.d { "deleteNotification - notificationId: $notificationId" }
        return notificationsRemoteDataSource.deleteNotification(notificationId = notificationId)
    }

    override suspend fun getNotificationSettings(): NotificationSettingDto {
        val notificationSetting = notificationsRemoteDataSource.getNotificationSettings()

        return notificationSetting ?: throw NullPointerException("notificationSetting is null")
    }

    override suspend fun patchNotificationSettings(settings: NotificationSettingDto): NotificationSettingDto {
        val notificationSetting = notificationsRemoteDataSource.patchNotificationSettings(
            settings = settings,
        )

        if (notificationSetting == null) {
            throw NullPointerException("notificationSetting is null")
        }

        if (settings != notificationSetting) {
            error("Not match settings: $settings and notificationSetting: $notificationSetting")
        }

        return notificationSetting
    }
}
