package com.captures2024.soongan.domain.usecase.system.inapp

interface LaunchInquiryUseCase {

    suspend operator fun invoke(): Result<Unit>
}
