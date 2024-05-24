package com.captures2024.soongan.core.domain.usecase.token

import com.captures2024.soongan.core.domain.repository.TokenRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetAllTokenUseCase
@Inject
constructor(
    private val repository: TokenRepository
) {

    suspend operator fun invoke(
        accessToken: String,
        refreshToken: String
    ): Result<Unit> = runSuspendCatching {
        repository.setAccessToken(accessToken)
        repository.setRefreshToken(refreshToken)
    }

}