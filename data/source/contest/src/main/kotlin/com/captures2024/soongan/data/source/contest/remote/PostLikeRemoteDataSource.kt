package com.captures2024.soongan.data.source.contest.remote

import com.captures2024.soongan.core.model.dto.PostLikeDto

interface PostLikeRemoteDataSource {

    suspend fun putPostLike(
        postId: Long,
        contestType: String,
    ): PostLikeDto?

    suspend fun deletePostLike(
        postId: Long,
        contestType: String,
    ): PostLikeDto?
}
