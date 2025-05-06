package com.captures2024.soongan.core.domain.usecase.system

import com.captures2024.soongan.core.data.repository.SystemRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class LaunchTermsUseCase
@Inject
constructor(
    private val systemRepository: SystemRepository,
) {

    suspend operator fun invoke() = runSuspendCatching {
        systemRepository.launchInAppBrowser(
            url = "https://abyssinian-cherry-9fc.notion.site/5724dc92a43c4e7e94fd5ccf8ab0608b",
        )
    }
}