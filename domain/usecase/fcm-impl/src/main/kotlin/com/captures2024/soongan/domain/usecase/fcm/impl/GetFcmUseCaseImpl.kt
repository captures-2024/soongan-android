package com.captures2024.soongan.domain.usecase.fcm.impl

import com.captures2024.soongan.domain.repository.fcm.FcmRepository
import com.captures2024.soongan.domain.usecase.fcm.GetFcmUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetFcmUseCaseImpl
@Inject
constructor(
    private val repository: FcmRepository,
) : GetFcmUseCase {

    override suspend fun invoke(): Result<String> = runSuspendCatching {
        repository.getFcm()
    }
}
