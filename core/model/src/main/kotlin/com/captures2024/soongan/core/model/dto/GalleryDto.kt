package com.captures2024.soongan.core.model.dto

data class GalleryDto(
    val round: Int? = null,
    val subject: String = "",
    val posts: List<GalleryPostDto> = emptyList(),
    val hasNext: Boolean = false,
)
