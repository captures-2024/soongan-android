package com.captures2024.soongan.domain.usecase.notification.impl

import com.captures2024.soongan.domain.repository.notification.NotificationRepository
import com.captures2024.soongan.domain.usecase.notification.GetIsNotReadNotificationCacheFlowUseCase
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetIsNotReadNotificationCacheFlowUseCaseImpl
@Inject
constructor(
    private val notificationRepository: NotificationRepository,
) : GetIsNotReadNotificationCacheFlowUseCase {

    override fun invoke(): StateFlow<Boolean> = notificationRepository.isNotReadNotificationCache
}
