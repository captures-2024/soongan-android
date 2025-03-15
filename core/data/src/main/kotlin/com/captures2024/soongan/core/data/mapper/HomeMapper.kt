package com.captures2024.soongan.core.data.mapper

import com.captures2024.soongan.core.model.dto.ContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.model.network.response.home.GetHomeStatusContestInfoResponse
import com.captures2024.soongan.core.model.network.response.home.GetHomeStatusPostInfoResponse

fun GetHomeStatusContestInfoResponse.toContestInfoDto(): ContestInfoDto = ContestInfoDto(
    contestType = contestType,
    subject = subject,
    startAt = startAt,
    endAt = endAt,
)

fun GetHomeStatusPostInfoResponse.toPostInfoDto(): PostInfoDto = PostInfoDto(
    postId = postId,
    imageUrl = imageUrl,
    likeCount = likeCount,
    commentCount = commentCount,
)
