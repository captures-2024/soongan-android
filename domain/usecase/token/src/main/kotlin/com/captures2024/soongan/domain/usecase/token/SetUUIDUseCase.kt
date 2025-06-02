package com.captures2024.soongan.domain.usecase.token

interface SetUUIDUseCase {

    suspend operator fun invoke(uuid: Long): Result<Unit>
}
