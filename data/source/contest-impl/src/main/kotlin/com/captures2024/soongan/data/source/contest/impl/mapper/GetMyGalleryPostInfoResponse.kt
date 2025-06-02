package com.captures2024.soongan.data.source.contest.impl.mapper

import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetMyGalleryPostInfoResponse

internal fun GetMyGalleryPostInfoResponse.toGalleryPostDto(): GalleryPostDto = GalleryPostDto(
    postId = this.postId,
    imageUrl = this.imageUrl,
)
