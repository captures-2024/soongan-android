package com.captures2024.soongan.domain.usecase.contest

interface RegisterPostUseCase {

    suspend operator fun invoke(
        title: String,
        imageFile: String,
    ): Result<Long>
}
