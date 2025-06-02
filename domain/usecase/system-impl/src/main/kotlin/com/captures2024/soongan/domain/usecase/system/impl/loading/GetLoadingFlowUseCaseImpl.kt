package com.captures2024.soongan.domain.usecase.system.impl.loading

import com.captures2024.soongan.data.repository.system.LoadingRepository
import com.captures2024.soongan.domain.usecase.system.loading.GetLoadingFlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoadingFlowUseCaseImpl
@Inject
constructor(
    private val repository: LoadingRepository,
) : GetLoadingFlowUseCase {

    override fun invoke(): Flow<Boolean> = repository.loadingFlow
}
