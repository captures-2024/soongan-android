package com.captures2024.soongan.data.source.home.impl.remote

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.HomeContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.data.source.home.impl.mapper.toHomeContestInfoDto
import com.captures2024.soongan.data.source.home.impl.mapper.toPostInfoDto
import com.captures2024.soongan.data.service.api.HomeService
import com.captures2024.soongan.data.source.home.remote.HomeRemoteDataSource
import com.captures2024.soongan.data.service.api.utils.safeAPICall
import javax.inject.Inject

class HomeRemoteDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val homeService: HomeService,
) : HomeRemoteDataSource {

    init {
        analyticsHelper.d { "HomeRemoteDataSource::init" }
    }

    override suspend fun getHomeStatus(): Pair<HomeContestInfoDto, List<PostInfoDto>>? {
        analyticsHelper.d { "getHomeStatus - entry" }

        val response = safeAPICall { homeService.getHomeStatusWithToken() }

        val responseHeader = response.headers

        analyticsHelper.d { "getHomeStatus - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getHomeStatus - responseBody: $responseBody" }

        val responseData = responseBody?.responseData

        if (responseData == null) {
            return null
        }

        return responseData.contestInfo.toHomeContestInfoDto() to responseData.postInfo.map { it.toPostInfoDto() }
    }

    override suspend fun getHomeStatusByGuest(): Pair<HomeContestInfoDto, List<PostInfoDto>>? {
        analyticsHelper.d { "getHomeStatusByGuest - entry" }

        val response = safeAPICall { homeService.getHomeStatusWithGuest() }

        val responseHeader = response.headers

        analyticsHelper.d { "getHomeStatusByGuest - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getHomeStatusByGuest - responseBody: $responseBody" }

        val responseData = responseBody?.responseData

        if (responseData == null) {
            return null
        }

        return responseData.contestInfo.toHomeContestInfoDto() to responseData.postInfo.map { it.toPostInfoDto() }
    }
}
