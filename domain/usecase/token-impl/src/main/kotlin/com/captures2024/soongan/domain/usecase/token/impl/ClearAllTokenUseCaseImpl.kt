package com.captures2024.soongan.domain.usecase.token.impl

import com.captures2024.soongan.domain.repository.token.TokenRepository
import com.captures2024.soongan.domain.usecase.token.ClearAllTokenUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class ClearAllTokenUseCaseImpl
@Inject
constructor(
    private val repository: TokenRepository,
) : ClearAllTokenUseCase {

    override suspend fun invoke(): Result<Unit> = runSuspendCatching {
        repository.clearAllToken()
    }
}
