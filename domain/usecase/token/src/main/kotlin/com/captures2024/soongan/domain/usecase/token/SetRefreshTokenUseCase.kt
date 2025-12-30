package com.captures2024.soongan.domain.usecase.token

interface SetRefreshTokenUseCase {

    suspend operator fun invoke(refreshToken: String): Result<Unit>
}
