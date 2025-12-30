package com.captures2024.soongan.domain.usecase.token

interface GetAccessTokenUseCase {

    suspend operator fun invoke(): Result<String>
}
