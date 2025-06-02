package com.captures2024.soongan.domain.usecase.fcm.impl

import com.captures2024.soongan.data.repository.fcm.FcmRepository
import com.captures2024.soongan.domain.usecase.fcm.InitFcmUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class InitFcmUseCaseImpl
@Inject
constructor(
    private val repository: FcmRepository,
) : InitFcmUseCase {

    override suspend fun invoke(): Result<Boolean> = runSuspendCatching {
        repository.initFcm().result
    }
}
