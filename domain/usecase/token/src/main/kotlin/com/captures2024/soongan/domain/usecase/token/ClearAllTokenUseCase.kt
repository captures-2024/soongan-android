package com.captures2024.soongan.domain.usecase.token

interface ClearAllTokenUseCase {

    suspend operator fun invoke(): Result<Unit>
}
