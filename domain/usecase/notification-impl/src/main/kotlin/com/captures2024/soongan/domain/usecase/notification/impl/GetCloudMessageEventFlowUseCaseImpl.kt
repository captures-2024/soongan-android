package com.captures2024.soongan.domain.usecase.notification.impl

import com.captures2024.soongan.core.model.dto.fcm.CloudMessage
import com.captures2024.soongan.domain.repository.notification.NotificationRepository
import com.captures2024.soongan.domain.usecase.notification.GetCloudMessageEventFlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCloudMessageEventFlowUseCaseImpl
@Inject
constructor(
    private val notificationRepository: NotificationRepository,
) : GetCloudMessageEventFlowUseCase {

    override fun invoke(): Flow<CloudMessage?> {
        return notificationRepository.cloudMessageEvent
    }
}
