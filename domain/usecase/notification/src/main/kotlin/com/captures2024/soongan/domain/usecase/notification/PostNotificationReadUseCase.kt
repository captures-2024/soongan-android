package com.captures2024.soongan.domain.usecase.notification

interface PostNotificationReadUseCase {

    suspend operator fun invoke(notificationId: Long): Result<Boolean>
}
