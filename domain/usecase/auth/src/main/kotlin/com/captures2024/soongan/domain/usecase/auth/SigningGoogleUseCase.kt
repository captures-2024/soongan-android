package com.captures2024.soongan.domain.usecase.auth

interface SigningGoogleUseCase {

    suspend operator fun invoke(
        token: String,
        fcmToken: String,
    ): Result<Boolean>
}
