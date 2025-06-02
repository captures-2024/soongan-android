package com.captures2024.soongan.domain.usecase.system.inapp

interface LaunchPrivacyPolicyUseCase {

    suspend operator fun invoke(): Result<Unit>
}
