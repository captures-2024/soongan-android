package com.captures2024.soongan.domain.usecase.contest

interface DeletePostUseCase {

    suspend operator fun invoke(postId: Long): Result<Boolean>
}
