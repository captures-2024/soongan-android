package com.captures2024.soongan.domain.usecase.fcm

interface GetFcmUseCase {

    suspend operator fun invoke(): Result<String>
}
