package com.captures2024.soongan.domain.usecase.token.impl

import com.captures2024.soongan.data.repository.token.TokenRepository
import com.captures2024.soongan.domain.usecase.token.GetRefreshTokenUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetRefreshTokenUseCaseImpl
@Inject
constructor(
    private val repository: TokenRepository,
) : GetRefreshTokenUseCase {

    override suspend fun invoke(): Result<String> = runSuspendCatching {
        repository.getRefreshToken()
    }
}
