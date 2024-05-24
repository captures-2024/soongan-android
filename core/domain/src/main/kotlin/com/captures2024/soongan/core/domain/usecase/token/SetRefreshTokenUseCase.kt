package com.captures2024.soongan.core.domain.usecase.token

import com.captures2024.soongan.core.domain.repository.TokenRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetRefreshTokenUseCase
@Inject
constructor(
    private val repository: TokenRepository
) {

    suspend operator fun invoke(refreshToken: String) = runSuspendCatching {
        repository.setRefreshToken(refreshToken)
    }

}