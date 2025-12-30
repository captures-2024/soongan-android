package com.captures2024.soongan.domain.usecase.notification

import com.captures2024.soongan.core.model.dto.NotificationsInfoDto
import com.captures2024.soongan.core.model.utils.NotificationType

interface GetNotificationsUseCase {

    suspend operator fun invoke(type: NotificationType): Result<NotificationsInfoDto>
}
