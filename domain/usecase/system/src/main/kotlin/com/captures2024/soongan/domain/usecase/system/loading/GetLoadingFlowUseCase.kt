package com.captures2024.soongan.domain.usecase.system.loading

import kotlinx.coroutines.flow.Flow

interface GetLoadingFlowUseCase {

    operator fun invoke(): Flow<Boolean>
}
