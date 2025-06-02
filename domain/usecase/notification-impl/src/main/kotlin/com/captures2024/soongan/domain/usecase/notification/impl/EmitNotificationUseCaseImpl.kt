package com.captures2024.soongan.domain.usecase.notification.impl

import com.captures2024.soongan.data.repository.notification.NotificationRepository
import com.captures2024.soongan.domain.usecase.notification.EmitNotificationUseCase
import javax.inject.Inject

class EmitNotificationUseCaseImpl
@Inject
constructor(
    private val repository: NotificationRepository,
) : EmitNotificationUseCase {

    override fun invoke(payload: Map<String, Any?>) {
        repository.emitNotification(payload)
    }
}
