package com.captures2024.soongan.domain.usecase.token.impl

import com.captures2024.soongan.data.repository.token.TokenRepository
import com.captures2024.soongan.domain.usecase.token.GetUUIDUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class GetUUIDUseCaseImpl
@Inject
constructor(
    private val repository: TokenRepository,
) : GetUUIDUseCase {

    override suspend fun invoke(): Result<Long> = runSuspendCatching {
        repository.getUUID()
    }
}
