package com.captures2024.soongan.core.model.dto.awards

data class AwardsDetailDto(
    val postsCount: Long,
    val firstPrizePost: AwardsPostDto,
    val otherTop7Posts: List<AwardsPostDto>,
)
