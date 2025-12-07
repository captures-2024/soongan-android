package com.captures2024.soongan.domain.usecase.system.impl.inapp

import com.captures2024.soongan.core.model.AppConst.InAppBrowser.PRIVACY_POLICY
import com.captures2024.soongan.domain.repository.system.SystemRepository
import com.captures2024.soongan.domain.usecase.system.inapp.LaunchPrivacyPolicyUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class LaunchPrivacyPolicyUseCaseImpl
@Inject
constructor(
    private val systemRepository: SystemRepository,
) : LaunchPrivacyPolicyUseCase {

    override suspend fun invoke(): Result<Unit> = runSuspendCatching {
        systemRepository.launchInAppBrowser(
            url = PRIVACY_POLICY,
        )
    }
}
