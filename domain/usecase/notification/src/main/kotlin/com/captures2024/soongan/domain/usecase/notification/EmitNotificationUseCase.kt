package com.captures2024.soongan.domain.usecase.notification

interface EmitNotificationUseCase {

    operator fun invoke(payload: Map<String, Any?>)
}
