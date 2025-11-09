package com.captures2024.soongan.domain.usecase.system.impl.appversion

import com.captures2024.soongan.domain.repository.system.SystemRepository
import com.captures2024.soongan.domain.usecase.system.appversion.GetIsUpdateAvailableFlowUseCase
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GetIsUpdateAvailableFlowUseCaseImpl
@Inject
constructor(
    private val systemRepository: SystemRepository,
) : GetIsUpdateAvailableFlowUseCase {

    override fun invoke(): SharedFlow<Boolean> = systemRepository.isAppUpdateAvailable
}
