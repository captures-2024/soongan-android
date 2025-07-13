package com.captures2024.soongan.data.source.awards.impl.remote

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.awards.AwardsDefaultDto
import com.captures2024.soongan.core.model.dto.awards.AwardsDetailDto
import com.captures2024.soongan.data.service.api.AwardsAPI
import com.captures2024.soongan.data.service.api.utils.safeAPICall
import com.captures2024.soongan.data.source.awards.impl.mapper.toAwardsDefaultDto
import com.captures2024.soongan.data.source.awards.impl.mapper.toAwardsDetailDto
import com.captures2024.soongan.data.source.awards.remote.AwardsRemoteDataSource
import javax.inject.Inject

class AwardsRemoteDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val awardsAPI: AwardsAPI,
) : AwardsRemoteDataSource {

    init {
        analyticsHelper.d { "AwardsRemoteDataSourceImpl::init" }
    }

    override suspend fun getAwardsList(): List<AwardsDefaultDto>? {
        analyticsHelper.d { "getAwardsList" }

        val response = safeAPICall {
            awardsAPI.getAwardsList()
        }

        val responseHeader = response.headers

        analyticsHelper.d { "getAwardsList - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getAwardsList - responseBody: $responseBody" }

        return responseBody?.responseData
            ?.contests
            ?.map { it.toAwardsDefaultDto() }
    }

    override suspend fun getAwardsDetailInfo(
        contestId: Long,
    ): AwardsDetailDto? {
        analyticsHelper.d { "getAwardsDetailInfo - contestId: $contestId" }

        val response = safeAPICall {
            awardsAPI.getAwardsDetailInfo(
                contestId = contestId,
            )
        }

        val responseHeader = response.headers

        analyticsHelper.d { "getAwardsDetailInfo - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "getAwardsDetailInfo - responseBody: $responseBody" }

        return responseBody?.responseData?.toAwardsDetailDto()
    }
}
