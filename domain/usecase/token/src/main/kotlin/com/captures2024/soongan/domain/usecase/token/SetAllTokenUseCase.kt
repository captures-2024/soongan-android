package com.captures2024.soongan.domain.usecase.token

interface SetAllTokenUseCase {

    suspend operator fun invoke(
        accessToken: String,
        refreshToken: String,
    ): Result<Unit>
}
