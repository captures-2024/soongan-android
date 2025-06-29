package com.captures2024.soongan.domain.usecase.token.impl

import com.captures2024.soongan.domain.repository.token.TokenRepository
import com.captures2024.soongan.domain.usecase.token.SetAllTokenUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class SetAllTokenUseCaseImpl
@Inject
constructor(
    private val repository: TokenRepository,
) : SetAllTokenUseCase {

    override suspend fun invoke(
        accessToken: String,
        refreshToken: String,
    ): Result<Unit> = runSuspendCatching {
        repository.setAccessToken(accessToken)
        repository.setRefreshToken(refreshToken)
    }
}
