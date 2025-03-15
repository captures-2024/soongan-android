package com.captures2024.soongan.core.data.mapper

import com.captures2024.soongan.core.model.dto.PostLikeDto
import com.captures2024.soongan.core.model.network.response.like.PostLikeResponse

fun PostLikeResponse.toPostLikeDto(): PostLikeDto = PostLikeDto(
    postId = postId,
    likeCount = likeCount
)