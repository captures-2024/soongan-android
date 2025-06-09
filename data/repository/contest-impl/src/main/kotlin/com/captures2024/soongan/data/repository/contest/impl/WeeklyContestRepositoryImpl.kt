package com.captures2024.soongan.data.repository.contest.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.dto.MyGalleryDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.model.dto.WeeklyContestInfoListDto
import com.captures2024.soongan.data.repository.contest.WeeklyContestRepository
import com.captures2024.soongan.data.source.contest.local.ContentVisibilityLocalDataSource
import com.captures2024.soongan.data.source.contest.remote.WeeklyContestRemoteDataSource
import com.captures2024.soongan.data.source.member.local.GuestLocalDataSource
import javax.inject.Inject

class WeeklyContestRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val weeklyContestRemoteDataSource: WeeklyContestRemoteDataSource,
    private val guestLocalDataSource: GuestLocalDataSource,
    private val contentVisibilityLocalDataSource: ContentVisibilityLocalDataSource,
) : WeeklyContestRepository {

    init {
        analyticsHelper.d { "WeeklyContestRepository::init" }
    }

    override suspend fun getGalleryInfo(
        round: Int?,
        orderType: String,
        page: Int,
        pageSize: Int,
    ): GalleryDto {
        val galleryInfo = weeklyContestRemoteDataSource.getGalleryInfo(
            round = round,
            orderType = orderType,
            page = page,
            pageSize = pageSize,
        )

        return galleryInfo ?: throw NullPointerException("galleryDto is null")
    }

    override suspend fun registerPost(
        title: String,
        imageFile: String,
    ): PostInfoDto {
        val postInfoDto = weeklyContestRemoteDataSource.registerPost(
            title = title,
            imageFile = imageFile,
        )

        if (postInfoDto == null) {
            throw NullPointerException("postInfoDto is null")
        }

        contentVisibilityLocalDataSource.emitRegisterPostEvent()

        return postInfoDto
    }

    override suspend fun getPostInfo(postId: Long): PostInfoDto {
        val postInfoDto = when (guestLocalDataSource.isGuestMode.value) {
            true -> weeklyContestRemoteDataSource.getPostInfoByGuest(postId = postId)

            false -> weeklyContestRemoteDataSource.getPostInfo(postId = postId)
        }

        return postInfoDto ?: throw NullPointerException("postInfoDto is null")
    }

    override suspend fun deletePost(postId: Long): Boolean {
        val result = weeklyContestRemoteDataSource.deletePost(postId = postId)

        if (result) {
            contentVisibilityLocalDataSource.emitHidePostEvent(postId = postId)
        }

        return result
    }

    override suspend fun editPostTitle(postId: Long, title: String): String {
        val editedTitle = weeklyContestRemoteDataSource.editPostTitle(
            postId = postId,
            title = title,
        )

        return editedTitle ?: throw NullPointerException("editedTitle is null")
    }

    override suspend fun getWeeklyContestInfoList(): WeeklyContestInfoListDto {
        val weeklyContestInfoListDto = weeklyContestRemoteDataSource.getWeeklyContestInfoList()

        return weeklyContestInfoListDto ?: throw NullPointerException("weeklyContestInfoListDto is null")
    }

    override suspend fun getMyGalleryInfo(page: Int, pageSize: Int): MyGalleryDto {
        val myGalleryInfo = weeklyContestRemoteDataSource.getMyGalleryInfo(
            page = page,
            pageSize = pageSize,
        )

        return myGalleryInfo ?: throw NullPointerException("myGalleryDto is null")
    }
}
