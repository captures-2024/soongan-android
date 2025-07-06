package com.captures2024.soongan.data.source.awards.impl.mapper

import com.captures2024.soongan.core.model.dto.awards.AwardsDetailDto
import com.captures2024.soongan.core.model.network.response.awards.GetAwardsDetailInfoResponse

internal fun GetAwardsDetailInfoResponse.toAwardsDetailDto(): AwardsDetailDto = AwardsDetailDto(
    postsCount = this.postsCount,
    firstPrizePost = this.firstPrizePost.toAwardsPostDto(),
    otherTop7Posts = this.otherTop7Posts.map { it.toAwardsPostDto() },
)
