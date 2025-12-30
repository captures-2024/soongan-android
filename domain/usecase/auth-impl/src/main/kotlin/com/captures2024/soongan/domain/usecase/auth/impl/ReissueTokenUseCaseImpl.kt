package com.captures2024.soongan.domain.usecase.auth.impl

import com.captures2024.soongan.domain.repository.auth.AuthRepository
import com.captures2024.soongan.domain.usecase.auth.ReissueTokenUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class ReissueTokenUseCaseImpl
@Inject
constructor(
    private val repository: AuthRepository,
) : ReissueTokenUseCase {

    override suspend operator fun invoke(): Result<Boolean> = runSuspendCatching {
        repository.reissueToken().result
    }
}
