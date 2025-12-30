package com.captures2024.soongan.domain.usecase.fcm

interface InitFcmUseCase {

    suspend operator fun invoke(): Result<Boolean>
}
