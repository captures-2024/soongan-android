package com.captures2024.soongan.core.domain.usecase.system

import com.captures2024.soongan.core.data.repository.SystemRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class LaunchPrivacyPolicyUseCase
@Inject
constructor(
    private val systemRepository: SystemRepository,
) {

    suspend operator fun invoke() = runSuspendCatching {
        systemRepository.launchInAppBrowser(
            url = "https://abyssinian-cherry-9fc.notion.site/71392fc225bf47b69e353739a74829db?pvs=4",
        )
    }
}