package com.captures2024.soongan.domain.usecase.member

import kotlinx.coroutines.flow.StateFlow

interface GetGuestModeFlowUseCase {

    operator fun invoke(): StateFlow<Boolean>
}
