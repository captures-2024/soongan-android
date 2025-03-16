package com.captures2024.soongan.core.data.repository

import com.captures2024.soongan.core.model.dto.ResultConditionDto

interface PostLikeRepository {

    suspend fun putPostLike(
        postId: Long,
        contestType: String,
    ): ResultConditionDto

    suspend fun deletePostLike(
        postId: Long,
        contestType: String,
    ): ResultConditionDto
}
