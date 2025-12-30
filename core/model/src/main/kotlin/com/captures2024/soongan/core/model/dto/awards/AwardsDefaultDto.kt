package com.captures2024.soongan.core.model.dto.awards

data class AwardsDefaultDto(
    val id: Long,
    val round: Int,
    val subject: String,
    val startAt: String,
    val endAt: String,
    val announcedAt: String,
    val thumbnailImageUrl: String,
)
