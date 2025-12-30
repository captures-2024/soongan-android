package com.captures2024.soongan.domain.usecase.notification

import com.captures2024.soongan.core.model.dto.NotificationsCountInfoDto

interface GetUnreadNotificationsCountUseCase {

    suspend operator fun invoke(): Result<NotificationsCountInfoDto>
}
