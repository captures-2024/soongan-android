package com.captures2024.soongan.data.source.awards.impl.mapper

import com.captures2024.soongan.core.model.dto.awards.AwardsPostDto
import com.captures2024.soongan.core.model.enums.AwardsPostStatusType
import com.captures2024.soongan.core.model.network.response.awards.GetOtherTop7PostResponse

internal fun GetOtherTop7PostResponse.toAwardsPostDto(): AwardsPostDto = AwardsPostDto(
    postId = this.postId,
    title = "",
    imageUrl = this.imageUrl,
    nickname = this.nickname,
    score = this.score.toString(),
    status = AwardsPostStatusType.from(this.status),
)
