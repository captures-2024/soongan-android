package com.captures2024.soongan.domain.usecase.system.impl.inapp

import com.captures2024.soongan.core.model.AppConst.InAppBrowser.TERMS
import com.captures2024.soongan.domain.repository.system.SystemRepository
import com.captures2024.soongan.domain.usecase.system.inapp.LaunchTermsUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class LaunchTermsUseCaseImpl
@Inject
constructor(
    private val systemRepository: SystemRepository,
) : LaunchTermsUseCase {

    override suspend fun invoke(): Result<Unit> = runSuspendCatching {
        systemRepository.launchInAppBrowser(
            url = TERMS,
        )
    }
}
