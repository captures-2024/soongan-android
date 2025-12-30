package com.captures2024.soongan.domain.usecase.notification.impl

import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.domain.repository.notification.NotificationRepository
import com.captures2024.soongan.domain.usecase.notification.GetNotificationEventFlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotificationEventFlowUseCaseImpl
@Inject
constructor(
    private val notificationRepository: NotificationRepository,
) : GetNotificationEventFlowUseCase {

    override fun invoke(): Flow<NotificationDto?> {
        return notificationRepository.notificationEvent
    }
}
