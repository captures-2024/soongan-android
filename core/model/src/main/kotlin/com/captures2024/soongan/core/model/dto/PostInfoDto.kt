package com.captures2024.soongan.core.model.dto

data class PostInfoDto(
    val memberId: Long? = null,
    val postId: Long = -1,
    val title: String = "",
    val imageUrl: String = "",
    val nickname: String = "",
    val likeCount: Int = 0,
    val isLiked: Boolean = false,
    val commentCount: Int = 0,
)
