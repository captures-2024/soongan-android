package com.captures2024.soongan.domain.usecase.system.impl.inapp

import com.captures2024.soongan.domain.repository.system.SystemRepository
import com.captures2024.soongan.domain.usecase.system.inapp.LaunchInquiryUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class LaunchInquiryUseCaseImpl
@Inject
constructor(
    private val systemRepository: SystemRepository,
) : LaunchInquiryUseCase {

    override suspend fun invoke(): Result<Unit> = runSuspendCatching {
        systemRepository.launchInAppBrowser(
            url = "https://forms.gle/sPEVxtXh9Kt4mVm36",
        )
    }
}
