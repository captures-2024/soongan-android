package com.captures2024.soongan.domain.usecase.system.inapp

import kotlinx.coroutines.flow.SharedFlow

interface GetInAppBrowserUrlFlowUseCase {

    operator fun invoke(): SharedFlow<String>
}
