package com.captures2024.soongan.domain.usecase.notification

import com.captures2024.soongan.core.model.dto.NotificationDto
import kotlinx.coroutines.flow.Flow

interface GetNotificationEventFlowUseCase {

    operator fun invoke(): Flow<NotificationDto?>
}
