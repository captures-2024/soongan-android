package com.captures2024.soongan.core.model.network.response.weekly.contests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMyGalleryResponse(
    @SerialName("postInfo")
    val posts: List<GetMyGalleryPostInfoResponse>,
    @SerialName("pageInfo")
    val pageInfo: GetGalleryPageInfoResponse,
)

// temp - api 수정 후 GetGalleryPostInfoResponse와 통합 예정
@Serializable
data class GetMyGalleryPostInfoResponse(
    @SerialName("round")
    val round: Int,
    @SerialName("subject")
    val subject: String,
    @SerialName("postId")
    val postId: Long,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("likeCount")
    val likeCount: Int,
)
