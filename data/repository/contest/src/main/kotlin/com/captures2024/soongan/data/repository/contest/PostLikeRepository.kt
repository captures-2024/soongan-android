package com.captures2024.soongan.data.repository.contest

import com.captures2024.soongan.core.model.dto.PostLikeDto

interface PostLikeRepository {

    suspend fun putPostLike(
        postId: Long,
        contestType: String,
    ): PostLikeDto

    suspend fun deletePostLike(
        postId: Long,
        contestType: String,
    ): PostLikeDto
}
