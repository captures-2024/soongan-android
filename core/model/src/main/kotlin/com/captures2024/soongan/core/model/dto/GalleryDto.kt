package com.captures2024.soongan.core.model.dto

data class GalleryDto(
    val round: Int? = null,
    val subject: String = "",
    val posts: List<GalleryPostDto> = emptyList(),
    val hasNext: Boolean = false,
)

data class GalleryPostDto(
    val nickname: String = "",
    val profileImageUrl: String = "",
    val postId: Int = -1,
    val imageUrl: String = "",
)