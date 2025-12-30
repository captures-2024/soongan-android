package com.captures2024.soongan.data.source.contest.impl.mapper

import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.model.network.response.weekly.contests.RegisterPostResponse

internal fun RegisterPostResponse.toPostInfoDto(): PostInfoDto = PostInfoDto(
    postId = this.postId,
    title = this.title,
    imageUrl = this.imageUrl,
    nickname = this.registerNickname,
)
