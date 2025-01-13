package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.data.remote.WeeklyContestDataSource
import com.captures2024.soongan.core.data.repository.WeeklyContestRepository
import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import javax.inject.Inject

class WeeklyContestRepositoryImpl
@Inject
constructor(
    private val weeklyContestDataSource: WeeklyContestDataSource
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
        weeklyContestRound: Int,
        subject: String,
        imageFile: String
    ): PostInfoDto {
        val postInfoDto = weeklyContestDataSource.registerPost(
            weeklyContestRound = weeklyContestRound,
            subject = subject,
            imageFile = imageFile,
        )

        return postInfoDto ?: throw NullPointerException("postInfoDto is null")
    }
}