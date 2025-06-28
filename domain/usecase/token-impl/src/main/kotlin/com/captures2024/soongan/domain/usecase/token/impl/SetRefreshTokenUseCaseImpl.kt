package com.captures2024.soongan.domain.usecase.token.impl

import com.captures2024.soongan.domain.repository.token.TokenRepository
import com.captures2024.soongan.domain.usecase.token.SetRefreshTokenUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class SetRefreshTokenUseCaseImpl
@Inject
constructor(
    private val repository: TokenRepository,
) : SetRefreshTokenUseCase {

    override suspend fun invoke(refreshToken: String): Result<Unit> = runSuspendCatching {
        repository.setRefreshToken(refreshToken)
    }
}
