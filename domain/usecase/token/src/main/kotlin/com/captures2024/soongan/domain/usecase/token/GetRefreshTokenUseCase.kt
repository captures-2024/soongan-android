package com.captures2024.soongan.domain.usecase.token

interface GetRefreshTokenUseCase {

    suspend operator fun invoke(): Result<String>
}
