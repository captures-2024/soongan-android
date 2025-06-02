package com.captures2024.soongan.domain.usecase.auth

interface SigningKakaoUseCase {

    suspend operator fun invoke(
        token: String,
        fcmToken: String,
    ): Result<Boolean>
}
