package com.captures2024.soongan.core.data.remote

import com.captures2024.soongan.core.model.dto.PostLikeDto

interface PostLikeDataSource {

    suspend fun putPostLike(
        postId: Long,
        contestType: String,
    ): PostLikeDto?

    suspend fun deletePostLike(
        postId: Long,
        contestType: String,
    ): PostLikeDto?
}
