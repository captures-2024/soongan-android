package com.captures2024.soongan.core.domain.usecase.loading

import com.captures2024.soongan.core.data.repository.LoadingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoadingFlowUseCase
@Inject
constructor(
    private val repository: LoadingRepository,
) {

    operator fun invoke(): Flow<Boolean> = repository.loadingFlow
}