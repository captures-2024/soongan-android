package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.data.remote.WeeklyContestDataSource
import com.captures2024.soongan.core.data.repository.WeeklyContestRepository
import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.dto.MyGalleryDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import kotlinx.coroutines.delay
import javax.inject.Inject

class WeeklyContestRepositoryImpl
@Inject
constructor(
    private val weeklyContestDataSource: WeeklyContestDataSource,
) : WeeklyContestRepository {

    override suspend fun getGalleryInfo(
        round: Int?,
        orderType: String,
        page: Int,
        pageSize: Int,
    ): GalleryDto {
        val galleryInfo = weeklyContestDataSource.getGalleryInfo(
            round = round,
            orderType = orderType,
            page = page,
            pageSize = pageSize
        )

        return galleryInfo ?: throw java.lang.NullPointerException("galleryDto is null")
    }

    override suspend fun registerPost(
        title: String,
        imageFile: String,
    ): PostInfoDto {
        val postInfoDto = weeklyContestDataSource.registerPost(
            title = title,
            imageFile = imageFile,
        )

        return postInfoDto ?: throw NullPointerException("postInfoDto is null")
    }

    override suspend fun getMyGalleryInfo(page: Int, pageSize: Int): MyGalleryDto {
        val myGalleryInfo = weeklyContestDataSource.getMyGalleryInfo(
            page = page,
            pageSize = pageSize
        )

        return myGalleryInfo ?: throw java.lang.NullPointerException("MygalleryDto is null")
    }

    override suspend fun getPostInfo(postId: Int): PostInfoDto {
        // TODO using weeklyContestDataSource

        delay(200)

        return PostInfoDto(
            postId = 1,
            imageUrl = "https://storage.googleapis.com/soongan-dev-bkt/2/weekly/1/soongan_image-1739873705023.jpg",
            subject = "무제",
            registerNickname = "intexy12",
            likeCount = 0,
            commentCount = 0
        )
    }
}