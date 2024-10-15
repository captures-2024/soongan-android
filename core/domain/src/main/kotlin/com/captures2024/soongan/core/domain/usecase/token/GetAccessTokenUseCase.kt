package com.captures2024.soongan.core.domain.usecase.token

import com.captures2024.soongan.core.data.repository.TokenRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject

class GetAccessTokenUseCase
@Inject
constructor(
    private val repository: TokenRepository,
) {

    suspend operator fun invoke() = runSuspendCatching {
        repository.getAccessToken()
    }
}
