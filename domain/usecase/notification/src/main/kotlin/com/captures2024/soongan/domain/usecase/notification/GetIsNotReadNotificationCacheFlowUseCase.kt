package com.captures2024.soongan.domain.usecase.notification

import kotlinx.coroutines.flow.StateFlow

interface GetIsNotReadNotificationCacheFlowUseCase {

    operator fun invoke(): StateFlow<Boolean>
}
