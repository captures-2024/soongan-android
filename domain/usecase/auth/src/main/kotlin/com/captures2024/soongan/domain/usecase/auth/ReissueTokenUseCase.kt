package com.captures2024.soongan.domain.usecase.auth

interface ReissueTokenUseCase {

    suspend operator fun invoke(): Result<Boolean>
}
