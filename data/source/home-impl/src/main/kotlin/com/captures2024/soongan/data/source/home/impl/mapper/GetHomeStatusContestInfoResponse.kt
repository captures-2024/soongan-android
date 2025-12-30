package com.captures2024.soongan.data.source.home.impl.mapper

import com.captures2024.soongan.core.model.dto.HomeContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.model.enums.ContestStatus
import com.captures2024.soongan.core.model.network.response.home.GetHomeStatusContestInfoResponse
import com.captures2024.soongan.core.model.network.response.home.GetHomeStatusPostInfoResponse

internal fun GetHomeStatusContestInfoResponse.toHomeContestInfoDto(): HomeContestInfoDto = HomeContestInfoDto(
    contestType = contestType,
    subject = subject,
    startAt = startAt,
    endAt = endAt,
    status = ContestStatus.from(status),
)

internal fun GetHomeStatusPostInfoResponse.toPostInfoDto(): PostInfoDto = PostInfoDto(
    postId = postId,
    imageUrl = imageUrl,
    likeCount = likeCount,
    commentCount = commentCount,
    isLiked = isLiked,
)
