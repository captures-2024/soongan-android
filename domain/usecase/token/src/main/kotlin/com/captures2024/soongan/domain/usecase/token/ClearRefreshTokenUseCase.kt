package com.captures2024.soongan.domain.usecase.token

interface ClearRefreshTokenUseCase {

    suspend operator fun invoke(): Result<Unit>
}
