package com.captures2024.soongan.core.data.source.home.remote

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.mapper.toContestInfoDto
import com.captures2024.soongan.core.data.mapper.toPostInfoDto
import com.captures2024.soongan.core.data.service.HomeService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.model.dto.ContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
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

    override suspend fun getHomeStatus(): Pair<ContestInfoDto, List<PostInfoDto>>? {
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

        return responseData.contestInfo.toContestInfoDto() to responseData.postInfo.map { it.toPostInfoDto() }
    }
}
