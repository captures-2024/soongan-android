package com.captures2024.soongan.domain.usecase.system.impl.inapp

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
            url = "https://abyssinian-cherry-9fc.notion.site/71392fc225bf47b69e353739a74829db?pvs=4",
        )
    }
}
