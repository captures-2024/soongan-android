package com.captures2024.soongan.core.domain.usecase.auth

import com.captures2024.soongan.core.data.repository.AuthRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class ReissueTokenUseCase
@Inject
constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(): Result<Boolean> = runSuspendCatching {
        repository.reissueToken().result
    }
}
