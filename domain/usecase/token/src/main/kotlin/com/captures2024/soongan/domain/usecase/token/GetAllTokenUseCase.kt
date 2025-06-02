package com.captures2024.soongan.domain.usecase.token

interface GetAllTokenUseCase {

    suspend operator fun invoke(): Result<Pair<String, String>>
}
