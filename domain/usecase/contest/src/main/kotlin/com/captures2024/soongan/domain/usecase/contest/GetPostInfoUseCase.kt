package com.captures2024.soongan.domain.usecase.contest

import com.captures2024.soongan.core.model.dto.PostInfoDto

interface GetPostInfoUseCase {

    suspend operator fun invoke(postId: Long): Result<PostInfoDto>
}
