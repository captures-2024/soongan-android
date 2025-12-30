package com.captures2024.soongan.data.source.awards.impl.mapper

import com.captures2024.soongan.core.model.dto.awards.AwardsDetailDto
import com.captures2024.soongan.core.model.network.response.awards.GetAwardsDetailInfoResponse

internal fun GetAwardsDetailInfoResponse.toAwardsDetailDto(): AwardsDetailDto {
    val sorted = this.otherTop7Posts.sortedBy { it.ranking }

    val prizePosts = listOf(this.firstPrizePost.toAwardsPostDto()) + sorted.map { it.toAwardsPostDto() }

    return AwardsDetailDto(
        subject = this.subject,
        round = this.round,
        startAt = this.startAt,
        endAt = this.endAt,
        postsCount = this.postsCount,
        prizePosts = prizePosts,
    )
}
