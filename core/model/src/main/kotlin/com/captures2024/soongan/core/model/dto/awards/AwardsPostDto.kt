package com.captures2024.soongan.core.model.dto.awards

import com.captures2024.soongan.core.model.enums.AwardsPostStatusType

data class AwardsPostDto(
    val postId: Long,
    val title: String,
    val imageUrl: String,
    val nickname: String,
    val score: String,
    val status: AwardsPostStatusType,
)
