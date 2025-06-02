package com.captures2024.soongan.data.source.contest.impl.mapper

import com.captures2024.soongan.core.model.dto.PostLikeDto
import com.captures2024.soongan.core.model.network.response.like.PostLikeResponse

internal fun PostLikeResponse.toPostLikeDto(): PostLikeDto = PostLikeDto(
    postId = postId,
    likeCount = likeCount,
)