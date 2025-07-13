package com.captures2024.soongan.data.source.awards.impl.mapper

import com.captures2024.soongan.core.model.dto.awards.AwardsPostDto
import com.captures2024.soongan.core.model.enums.AwardsPostStatusType
import com.captures2024.soongan.core.model.network.response.awards.GetAwardsDetailInfoPostResponse

internal fun GetAwardsDetailInfoPostResponse.toAwardsPostDto(): AwardsPostDto = AwardsPostDto(
    postId = this.postId,
    title = this.title,
    imageUrl = this.imageUrl,
    nickname = this.nickname,
    score = this.score,
    status = AwardsPostStatusType.from(this.status),
)
