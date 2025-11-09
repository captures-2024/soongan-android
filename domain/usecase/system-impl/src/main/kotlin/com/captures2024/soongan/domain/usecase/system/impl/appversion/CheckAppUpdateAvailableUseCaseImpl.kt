package com.captures2024.soongan.domain.usecase.system.impl.appversion

import com.captures2024.soongan.domain.repository.system.SystemRepository
import com.captures2024.soongan.domain.usecase.system.appversion.CheckAppUpdateAvailableUseCase
import javax.inject.Inject

class CheckAppUpdateAvailableUseCaseImpl
@Inject
constructor(
    private val systemRepository: SystemRepository,
) : CheckAppUpdateAvailableUseCase {

    override suspend fun invoke() = systemRepository.checkAppUpdateAvailable()
}
