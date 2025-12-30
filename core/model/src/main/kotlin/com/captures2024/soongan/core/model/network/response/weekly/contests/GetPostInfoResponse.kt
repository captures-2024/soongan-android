package com.captures2024.soongan.core.model.network.response.weekly.contests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPostInfoResponse(
    @SerialName("memberId")
    val memberId: Long?,
    @SerialName("postId")
    val postId: Long,
    @SerialName("title")
    val title: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("likeCount")
    val likeCount: Int,
    @SerialName("isLiked")
    val isLiked: Boolean,
    @SerialName("commentCount")
    val commentCount: Int,
    @SerialName("isTop7")
    val isTop7: Boolean,
    @SerialName("weeklyContestRound")
    val weeklyContestRound: Int,
    @SerialName("weeklyContestSubject")
    val weeklyContestSubject: String,
)
