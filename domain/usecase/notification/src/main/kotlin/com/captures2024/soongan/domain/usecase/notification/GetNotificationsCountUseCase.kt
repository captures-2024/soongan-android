package com.captures2024.soongan.domain.usecase.notification

import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto

interface GetNotificationsCountUseCase {

    suspend operator fun invoke(): Result<NotificationsCountInfoDto>
}
