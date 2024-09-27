package com.captures2024.soongan.core.domain.usecase.token

import com.captures2024.soongan.core.data.repository.TokenRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class SetAllTokenUseCase
@Inject
constructor(
    private val repository: TokenRepository,
) {

    suspend operator fun invoke(
        accessToken: String,
        refreshToken: String,
    ): Result<Unit> = runSuspendCatching {
        repository.setAccessToken(accessToken)
        repository.setRefreshToken(refreshToken)
    }
}
