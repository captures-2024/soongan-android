package com.captures2024.soongan.domain.usecase.notification.impl

import com.captures2024.soongan.domain.repository.notification.NotificationRepository
import com.captures2024.soongan.domain.usecase.notification.ClearNotificationEventUseCase
import javax.inject.Inject

class ClearNotificationEventUseCaseImpl
@Inject
constructor(
    private val notificationRepository: NotificationRepository,
) : ClearNotificationEventUseCase {

    override operator fun invoke() {
        notificationRepository.clearNotificationEvent()
    }
}
