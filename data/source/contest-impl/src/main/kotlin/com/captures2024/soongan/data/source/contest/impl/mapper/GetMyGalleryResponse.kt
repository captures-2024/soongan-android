package com.captures2024.soongan.data.source.contest.impl.mapper

import com.captures2024.soongan.core.model.dto.MyGalleryDto
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetMyGalleryResponse

internal fun GetMyGalleryResponse.toMyGalleryDto(): MyGalleryDto = MyGalleryDto(
    posts = this.posts.map { it.toGalleryPostDto() },
    hasNext = this.pageInfo.hasNext,
)
