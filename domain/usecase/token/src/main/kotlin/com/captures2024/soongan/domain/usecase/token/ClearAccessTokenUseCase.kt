package com.captures2024.soongan.domain.usecase.token

interface ClearAccessTokenUseCase {

    suspend operator fun invoke(): Result<Unit>
}
