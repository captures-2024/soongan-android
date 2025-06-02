package com.captures2024.soongan.domain.usecase.auth

interface SignOutSocialPlatformUseCase {

    suspend operator fun invoke(): Result<Boolean>
}
