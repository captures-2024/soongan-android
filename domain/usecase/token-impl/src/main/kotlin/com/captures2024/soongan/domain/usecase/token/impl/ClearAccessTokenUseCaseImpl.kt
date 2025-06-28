package com.captures2024.soongan.domain.usecase.token.impl

import com.captures2024.soongan.domain.repository.token.TokenRepository
import com.captures2024.soongan.domain.usecase.token.ClearAccessTokenUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class ClearAccessTokenUseCaseImpl
@Inject
constructor(
    private val repository: TokenRepository,
) : ClearAccessTokenUseCase {

    override suspend fun invoke(): Result<Unit> = runSuspendCatching {
        repository.clearAccessToken()
    }
}
