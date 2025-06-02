package com.captures2024.soongan.domain.usecase.system.impl.inapp

import com.captures2024.soongan.data.repository.system.SystemRepository
import com.captures2024.soongan.domain.usecase.system.inapp.GetInAppBrowserUrlFlowUseCase
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GetInAppBrowserUrlFlowUseCaseImpl
@Inject
constructor(
    private val systemRepository: SystemRepository,
) : GetInAppBrowserUrlFlowUseCase {

    override fun invoke(): SharedFlow<String> = systemRepository.inAppBrowserUrl
}
