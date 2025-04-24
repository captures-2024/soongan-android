package com.captures2024.soongan.core.domain.usecase.notifications

import com.captures2024.soongan.core.data.repository.NotificationsRepository
import javax.inject.Inject

class EmitNotificationUseCase
@Inject
constructor(
    private val repository: NotificationsRepository,
) {

    operator fun invoke(payload: Map<String, Any?>) = repository.emitNotification(payload)
}
