package com.captures2024.soongan.data.source.notification.impl.local

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.data.source.notification.local.NotificationLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotificationLocalDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
) : NotificationLocalDataSource {
    private val scope by lazy { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    private val _notificationToken = MutableSharedFlow<Map<String, String?>>(1)
    override val notificationToken: Flow<Map<String, String?>>
        get() = _notificationToken

    private val _notificationEvent = MutableStateFlow<NotificationDto?>(null)
    override val notificationEvent: Flow<NotificationDto?>
        get() = _notificationEvent

    init {
        analyticsHelper.d { "NotificationLocalDataSource::init" }
    }

    override fun emitNotification(payload: Map<String, Any?>) {
        analyticsHelper.d { "emitNotification - payload: $payload" }

        scope.launch {
            _notificationToken.emit(payload.mapValues { it.value?.toString() })
        }
    }

    override suspend fun parseNotification(payload: Map<String, Any?>) {
        analyticsHelper.d { "parseNotification - payload: $payload" }

        val notification = NotificationDto.Companion.fromPayload(payload)

        analyticsHelper.d { "parseNotification - notification: $notification" }

        _notificationEvent.value = notification
    }
}
