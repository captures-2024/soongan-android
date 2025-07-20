package com.captures2024.soongan.core.model.dto.awards

data class AwardsDetailDto(
    val subject: String,
    val round: Long,
    val startAt: String,
    val endAt: String,
    val postsCount: Long,
    val prizePosts: List<AwardsPostDto>,
)
