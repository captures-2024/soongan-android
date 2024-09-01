package com.captures2024.soongan.core.domain.usecase.token

import com.captures2024.soongan.core.data.repository.TokenRepository
import com.captures2024.soongan.core.domain.runSuspendCatching
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetUUIDUseCase
@Inject
constructor(
    private val repository: TokenRepository
) {

    suspend operator fun invoke(uuid: Long) = runSuspendCatching {
        repository.setUUID(uuid)
    }

}