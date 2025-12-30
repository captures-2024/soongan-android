package com.captures2024.soongan.data.source.contest.impl.mapper

import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetGalleryResponse

internal fun GetGalleryResponse.toGalleryDto(): GalleryDto = GalleryDto(
    round = this.round,
    subject = this.subject,
    posts = this.posts.map { it.toGalleryPostDto() },
    hasNext = this.pageInfo.hasNext,
)
