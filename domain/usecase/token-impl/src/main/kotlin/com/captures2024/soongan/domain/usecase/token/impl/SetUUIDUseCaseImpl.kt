package com.captures2024.soongan.domain.usecase.token.impl

import com.captures2024.soongan.domain.repository.token.TokenRepository
import com.captures2024.soongan.domain.usecase.token.SetUUIDUseCase
import com.captures2024.soongan.domain.usecase.utils.runSuspendCatching
import javax.inject.Inject

class SetUUIDUseCaseImpl
@Inject
constructor(
    private val repository: TokenRepository,
) : SetUUIDUseCase {

    override suspend fun invoke(uuid: Long): Result<Unit> = runSuspendCatching {
        repository.setUUID(uuid)
    }
}
