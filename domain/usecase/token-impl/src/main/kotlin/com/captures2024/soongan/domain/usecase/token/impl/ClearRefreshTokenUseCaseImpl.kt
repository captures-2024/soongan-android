package com.captures2024.soongan.domain.usecase.token.impl

import com.captures2024.soongan.data.repository.token.TokenRepository
import com.captures2024.soongan.domain.usecase.token.ClearRefreshTokenUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class ClearRefreshTokenUseCaseImpl
@Inject
constructor(
    private val repository: TokenRepository,
) : ClearRefreshTokenUseCase {

    override suspend fun invoke(): Result<Unit> = runSuspendCatching {
        repository.clearRefreshToken()
    }
}
