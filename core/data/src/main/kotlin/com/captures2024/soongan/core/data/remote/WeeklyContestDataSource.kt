package com.captures2024.soongan.core.data.remote

import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.dto.MyGalleryDto
import com.captures2024.soongan.core.model.dto.PostInfoDto

interface WeeklyContestDataSource {

    suspend fun getGalleryInfo(
        round: Int?,
        orderType: String,
        page: Int,
        pageSize: Int,
    ): GalleryDto?

    suspend fun registerPost(
        weeklyContestRound: Int,
        subject: String,
        imageFile: String,
    ): PostInfoDto?

    suspend fun getMyGalleryInfo(
        page: Int,
        pageSize: Int,
    ): MyGalleryDto?
}