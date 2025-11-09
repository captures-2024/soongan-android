package com.captures2024.soongan.domain.usecase.system.appversion

import kotlinx.coroutines.flow.SharedFlow

interface GetIsUpdateAvailableFlowUseCase {

    operator fun invoke(): SharedFlow<Boolean>
}
