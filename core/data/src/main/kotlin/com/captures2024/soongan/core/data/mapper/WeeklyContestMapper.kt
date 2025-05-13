package com.captures2024.soongan.core.data.mapper

import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.dto.GalleryPostDto
import com.captures2024.soongan.core.model.dto.MyGalleryDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.model.dto.WeeklyContestInfoDto
import com.captures2024.soongan.core.model.dto.WeeklyContestInfoListDto
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetGalleryPostInfoResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetGalleryResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetMyGalleryPostInfoResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetMyGalleryResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetPostInfoResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetWeeklyContestInfoListResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.GetWeeklyContestInfoResponse
import com.captures2024.soongan.core.model.network.response.weekly.contests.RegisterPostResponse

fun GetGalleryResponse.toGalleryDto(): GalleryDto = GalleryDto(
    round = this.round,
    subject = this.subject,
    posts = this.posts.map { it.toGalleryPostDto() },
    hasNext = this.pageInfo.hasNext,
)

fun GetMyGalleryResponse.toMyGalleryDto(): MyGalleryDto = MyGalleryDto(
    posts = this.posts.map { it.toGalleryPostDto() },
    hasNext = this.pageInfo.hasNext,
)

fun GetGalleryPostInfoResponse.toGalleryPostDto(): GalleryPostDto = GalleryPostDto(
    postId = this.postId,
    imageUrl = this.imageUrl,
)

fun GetMyGalleryPostInfoResponse.toGalleryPostDto(): GalleryPostDto = GalleryPostDto(
    postId = this.postId,
    imageUrl = this.imageUrl,
)

fun RegisterPostResponse.toPostInfoDto(): PostInfoDto = PostInfoDto(
    postId = this.postId,
    title = this.title,
    imageUrl = this.imageUrl,
    nickname = this.registerNickname,
)

fun GetPostInfoResponse.toPostInfoDto(): PostInfoDto = PostInfoDto(
    memberId = this.memberId,
    postId = this.postId,
    title = this.title,
    imageUrl = this.imageUrl,
    nickname = this.nickname,
    likeCount = this.likeCount,
    isLiked = this.isLiked,
    commentCount = this.commentCount,
)

fun GetWeeklyContestInfoListResponse.toWeeklyContestInfoListDto(): WeeklyContestInfoListDto =
    WeeklyContestInfoListDto(
        weeklyContestInfoList = this.weeklyContestInfoList.map { it.toWeeklyContestInfoDto() },
    )

fun GetWeeklyContestInfoResponse.toWeeklyContestInfoDto(): WeeklyContestInfoDto =
    WeeklyContestInfoDto(
        id = this.id,
        round = this.round,
        subject = this.subject,
        startAt = this.startAt,
        endAt = this.endAt,
        voteStartAt = this.voteStartAt,
        voteEndAt = this.voteEndAt,
        announcedAt = this.announcedAt,
    )
