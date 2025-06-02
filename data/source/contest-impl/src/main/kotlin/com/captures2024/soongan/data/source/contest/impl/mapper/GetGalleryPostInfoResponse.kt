package com.captures2024.soongan.data.source.contest.impl.mapper

import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetGalleryPostInfoResponse

internal fun GetGalleryPostInfoResponse.toGalleryPostDto(): GalleryPostDto = GalleryPostDto(
    postId = this.postId,
    imageUrl = this.imageUrl,
)
