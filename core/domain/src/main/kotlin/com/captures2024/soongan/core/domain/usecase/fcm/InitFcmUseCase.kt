package com.captures2024.soongan.core.domain.usecase.fcm

import com.captures2024.soongan.core.data.repository.FcmRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class InitFcmUseCase
@Inject
constructor(
    private val repository: FcmRepository,
) {

    suspend operator fun invoke(fcmToken: String) = runSuspendCatching {
        repository.initFcm(fcmToken)
    }
}
