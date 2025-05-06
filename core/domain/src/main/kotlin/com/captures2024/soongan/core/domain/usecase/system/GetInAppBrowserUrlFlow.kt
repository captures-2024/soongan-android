package com.captures2024.soongan.core.domain.usecase.system

import com.captures2024.soongan.core.data.repository.SystemRepository
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GetInAppBrowserUrlFlow
@Inject
constructor(
    private val systemRepository: SystemRepository,
) {

    operator fun invoke(): SharedFlow<String> = systemRepository.inAppBrowserUrl
}
