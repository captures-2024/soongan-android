package com.captures2024.soongan.core.model.dto

data class MyGalleryDto(
    val posts: List<GalleryPostDto> = emptyList(),
    val hasNext: Boolean = false,
)