package com.captures2024.soongan.domain.usecase.system.inapp

interface LaunchTermsUseCase {

    suspend operator fun invoke(): Result<Unit>
}
