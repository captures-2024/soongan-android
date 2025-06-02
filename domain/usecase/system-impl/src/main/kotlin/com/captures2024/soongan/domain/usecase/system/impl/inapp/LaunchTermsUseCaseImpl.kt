package com.captures2024.soongan.domain.usecase.system.impl.inapp

import com.captures2024.soongan.data.repository.system.SystemRepository
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
            url = "https://abyssinian-cherry-9fc.notion.site/5724dc92a43c4e7e94fd5ccf8ab0608b",
        )
    }
}
