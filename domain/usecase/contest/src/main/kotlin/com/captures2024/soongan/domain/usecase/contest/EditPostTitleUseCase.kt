package com.captures2024.soongan.domain.usecase.contest

interface EditPostTitleUseCase {

    suspend operator fun invoke(
        postId: Long,
        title: String,
    ): Result<String>
}
