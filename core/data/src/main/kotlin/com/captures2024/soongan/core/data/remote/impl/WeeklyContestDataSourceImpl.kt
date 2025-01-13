package com.captures2024.soongan.core.data.remote.impl

import android.content.Context
import com.captures2024.soongan.core.data.mapper.toGalleryDto
import com.captures2024.soongan.core.data.mapper.toPostInfoDto
import com.captures2024.soongan.core.data.remote.WeeklyContestDataSource
import com.captures2024.soongan.core.data.service.WeeklyContestService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.data.utils.toImageMultiPart
import com.captures2024.soongan.core.data.utils.toTextRequestBody
import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WeeklyContestDataSourceImpl
@Inject
constructor(
    @ApplicationContext private val context: Context,
    private val service: WeeklyContestService,
) : WeeklyContestDataSource {
    override suspend fun getGalleryInfo(
        round: Int?,
        orderType: String,
        page: Int,
        pageSize: Int,
    ): GalleryDto? = safeAPICall {
        service.getGalleryInfo(
            round = round,
            orderType = orderType,
            page = page,
            pageSize = pageSize
        )
    }.body?.responseData?.toGalleryDto()

    override suspend fun registerPost(
        weeklyContestRound: Int,
        subject: String,
        imageFile: String
    ): PostInfoDto? = safeAPICall {
        service.registerPost(
            weeklyContestRound = weeklyContestRound.toString().toTextRequestBody(),
            subject = subject.toTextRequestBody(),
            imageFile = imageFile.toImageMultiPart(context, "imageFile"),
        )
    }.body?.responseData?.toPostInfoDto()
}