package com.captures2024.soongan.domain.usecase.token.impl

import com.captures2024.soongan.data.repository.token.TokenRepository
import com.captures2024.soongan.domain.usecase.token.GetAllTokenUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetAllTokenUseCaseImpl
@Inject
constructor(
    private val repository: TokenRepository,
) : GetAllTokenUseCase {

    override suspend fun invoke(): Result<Pair<String, String>> = runSuspendCatching {
        val accessToken = repository.getAccessToken()
        val refreshToken = repository.getRefreshToken()

        accessToken to refreshToken
    }
}
