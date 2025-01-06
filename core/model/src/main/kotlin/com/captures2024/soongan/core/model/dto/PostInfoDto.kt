package com.captures2024.soongan.core.model.dto

data class PostInfoDto(
    val postId: Int = -1,
    val imageUrl: String = "",
    val likeCount: Int = 0,
    val commentCount: Int = 0,
)
