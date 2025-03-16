package com.captures2024.soongan.core.data.remote.impl

import android.content.Context
import com.captures2024.soongan.core.data.mapper.toGalleryDto
import com.captures2024.soongan.core.data.mapper.toMyGalleryDto
import com.captures2024.soongan.core.data.mapper.toPostInfoDto
import com.captures2024.soongan.core.data.remote.WeeklyContestDataSource
import com.captures2024.soongan.core.data.service.WeeklyContestService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.data.utils.toImageMultiPart
import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.dto.MyGalleryDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.model.network.request.weekly.contests.EditPostRequest
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
            pageSize = pageSize,
        )
    }.body?.responseData?.toGalleryDto()

    override suspend fun registerPost(
        title: String,
        imageFile: String,
    ): PostInfoDto? = safeAPICall {
        service.registerPost(
            title = title,
            imageFile = imageFile.toImageMultiPart(context, "imageFile"),
        )
    }.body?.responseData?.toPostInfoDto()

    override suspend fun getPostInfo(
        postId: Long,
    ): PostInfoDto? = safeAPICall {
        service.getPostInfo(
            postId = postId,
        )
    }.body?.responseData?.toPostInfoDto()

    override suspend fun deletePost(
        postId: Long,
    ): Boolean {
        val result = safeAPICall {
            service.deletePost(
                postId = postId,
            )
        }.body

        return when (result) {
            null -> false
            else -> true
        }
    }

    override suspend fun editPostTitle(
        postId: Long,
        title: String,
    ): String? = safeAPICall {
        service.editPostTitle(
            postId = postId,
            request = EditPostRequest(title = title),
        )
    }.body?.responseData?.title

    override suspend fun getMyGalleryInfo(
        page: Int,
        pageSize: Int,
    ): MyGalleryDto? = safeAPICall {
        service.getMyGalleryInfo(
            page = page,
            pageSize = pageSize,
        )
    }.body?.responseData?.toMyGalleryDto()
}
