package com.captures2024.soongan.data.source.contest.impl.mapper

import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetPostInfoResponse

internal fun GetPostInfoResponse.toPostInfoDto(): PostInfoDto = PostInfoDto(
    memberId = this.memberId,
    postId = this.postId,
    title = this.title,
    imageUrl = this.imageUrl,
    nickname = this.nickname,
    likeCount = this.likeCount,
    isLiked = this.isLiked,
    commentCount = this.commentCount,
)
