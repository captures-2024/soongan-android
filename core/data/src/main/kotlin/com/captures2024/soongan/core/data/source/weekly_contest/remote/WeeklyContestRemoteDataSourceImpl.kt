package com.captures2024.soongan.core.data.source.weekly_contest.remote

import android.content.Context
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.mapper.toGalleryDto
import com.captures2024.soongan.core.data.mapper.toMyGalleryDto
import com.captures2024.soongan.core.data.mapper.toPostInfoDto
import com.captures2024.soongan.core.data.service.WeeklyContestService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.data.utils.toImageMultiPart
import com.captures2024.soongan.core.model.dto.GalleryDto
import com.captures2024.soongan.core.model.dto.MyGalleryDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.core.model.network.request.weekly.contests.EditPostRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WeeklyContestRemoteDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    @ApplicationContext private val context: Context,
    private val service: WeeklyContestService,
) : WeeklyContestRemoteDataSource {

    init {
        analyticsHelper.d { "WeeklyContestRemoteDataSource::init" }
    }

    override suspend fun getGalleryInfo(
        round: Int?,
        orderType: String,
        page: Int,
        pageSize: Int,
    ): GalleryDto? {
        analyticsHelper.d { "getGalleryInfo - round: $round, orderType: $orderType, page: $page, pageSize: $pageSize" }

        val response = safeAPICall {
            service.getGalleryInfo(
                round = round,
                orderType = orderType,
                page = page,
                pageSize = pageSize,
            )
        }

        val responseHeader = response.headers

        analyticsHelper.d { "getGalleryInfo - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getGalleryInfo - responseBody: $responseBody" }

        return responseBody?.responseData?.toGalleryDto()
    }

    override suspend fun registerPost(
        title: String,
        imageFile: String,
    ): PostInfoDto? {
        analyticsHelper.d { "registerPost - title: $title, imageFile: $imageFile" }

        val response = safeAPICall {
            service.registerPost(
                title = title,
                imageFile = imageFile.toImageMultiPart(context, "imageFile"),
            )
        }

        val responseHeader = response.headers

        analyticsHelper.d { "registerPost - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "registerPost - responseBody: $responseBody" }

        return responseBody?.responseData?.toPostInfoDto()
    }

    override suspend fun getPostInfo(postId: Long): PostInfoDto? {
        analyticsHelper.d { "getPostInfo - postId: $postId" }

        val response = safeAPICall { service.getPostInfoWithToken(postId = postId) }

        val responseHeader = response.headers

        analyticsHelper.d { "getPostInfo - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getPostInfo - responseBody: $responseBody" }

        return responseBody?.responseData?.toPostInfoDto()
    }

    override suspend fun deletePost(postId: Long): Boolean {
        analyticsHelper.d { "deletePost - postId: $postId" }

        val response = safeAPICall { service.deletePost(postId = postId) }

        val responseHeader = response.headers

        analyticsHelper.d { "deletePost - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "deletePost - responseBody: $responseBody" }

        return when (responseBody) {
            null -> false
            else -> true
        }
    }

    override suspend fun editPostTitle(
        postId: Long,
        title: String,
    ): String? {
        analyticsHelper.d { "editPostTitle - postId: $postId, title: $title" }

        val response = safeAPICall {
            service.editPostTitle(
                postId = postId,
                request = EditPostRequest(title = title),
            )
        }

        val responseHeader = response.headers

        analyticsHelper.d { "editPostTitle - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "editPostTitle - responseBody: $responseBody" }

        return responseBody?.responseData?.title
    }

    override suspend fun getMyGalleryInfo(
        page: Int,
        pageSize: Int,
    ): MyGalleryDto? {
        analyticsHelper.d { "getMyGalleryInfo - page: $page, pageSize: $pageSize" }

        val response = safeAPICall {
            service.getMyGalleryInfo(
                page = page,
                pageSize = pageSize,
            )
        }

        val responseHeader = response.headers

        analyticsHelper.d { "getMyGalleryInfo - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getMyGalleryInfo - responseBody: $responseBody" }

        return responseBody?.responseData?.toMyGalleryDto()
    }
}
