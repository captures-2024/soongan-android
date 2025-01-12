package com.captures2024.soongan.core.data.mapper

import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.model.network.response.weekly.contests.RegisterPostResponse

fun RegisterPostResponse.toPostInfoDto(): PostInfoDto = PostInfoDto(
    postId = this.postId,
    subject = this.subject,
    imageUrl = this.imageUrl,
    registerNickname = this.registerNickname,
)