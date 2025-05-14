package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.source.weekly_contest.remote.WeeklyContestRemoteDataSource
import com.captures2024.soongan.core.data.repository.WeeklyContestRepository
import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.dto.MyGalleryDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.model.dto.WeeklyContestInfoListDto
import javax.inject.Inject

class WeeklyContestRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val weeklyContestRemoteDataSource: WeeklyContestRemoteDataSource,
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

        return postInfoDto ?: throw NullPointerException("postInfoDto is null")
    }

    override suspend fun getPostInfo(postId: Long): PostInfoDto {
        val postInfoDto = weeklyContestRemoteDataSource.getPostInfo(
            postId = postId,
        )

        return postInfoDto ?: throw NullPointerException("postInfoDto is null")
    }

    override suspend fun deletePost(postId: Long): Boolean =
        weeklyContestRemoteDataSource.deletePost(postId = postId)

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
