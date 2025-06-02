package com.captures2024.soongan.domain.usecase.contest

import com.captures2024.soongan.core.model.dto.PostLikeDto

interface PutPostLikeUseCase {

    suspend operator fun invoke(
        postId: Long,
        contestType: String,
    ): Result<PostLikeDto>
}
