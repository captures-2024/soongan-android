package com.captures2024.soongan.core.data.mapper

import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetGalleryResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetGalleryPostInfoResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.RegisterPostResponse

fun GetGalleryResponse.toGalleryDto(): GalleryDto = GalleryDto(
    round = this.round,
    subject = this.subject,
    posts = this.posts.map { it.toGalleryPostDto() },
    hasNext = this.pageInfo.hasNext
)

fun GetGalleryPostInfoResponse.toGalleryPostDto(): GalleryPostDto = GalleryPostDto(
    nickname = this.nickname,
    profileImageUrl = this.profileImageUrl,
    postId = this.postId,
    imageUrl = this.imageUrl
)

fun RegisterPostResponse.toPostInfoDto(): PostInfoDto = PostInfoDto(
    postId = this.postId,
    subject = this.subject,
    imageUrl = this.imageUrl,
    registerNickname = this.registerNickname,
)