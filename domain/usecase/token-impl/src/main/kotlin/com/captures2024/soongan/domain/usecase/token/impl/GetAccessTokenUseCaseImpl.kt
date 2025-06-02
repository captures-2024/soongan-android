package com.captures2024.soongan.domain.usecase.token.impl

import com.captures2024.soongan.data.repository.token.TokenRepository
import com.captures2024.soongan.domain.usecase.token.GetAccessTokenUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetAccessTokenUseCaseImpl
@Inject
constructor(
    private val repository: TokenRepository,
) : GetAccessTokenUseCase {

    override suspend fun invoke(): Result<String> = runSuspendCatching {
        repository.getAccessToken()
    }
}
