package com.captures2024.soongan.domain.usecase.notification.impl

import com.captures2024.soongan.domain.repository.notification.NotificationRepository
import com.captures2024.soongan.domain.usecase.notification.ClearCloudMessageEventUseCase
import javax.inject.Inject

class ClearCloudMessageEventUseCaseImpl
@Inject
constructor(
    private val notificationRepository: NotificationRepository,
) : ClearCloudMessageEventUseCase {

    override operator fun invoke() {
        notificationRepository.clearCloudMessageEvent()
    }
}
