package com.captures2024.soongan.core.data.remote.impl

import com.captures2024.soongan.core.data.mapper.toContestInfoDto
import com.captures2024.soongan.core.data.mapper.toPostInfoDto
import com.captures2024.soongan.core.data.remote.HomeDataSource
import com.captures2024.soongan.core.data.service.HomeService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.model.dto.ContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import javax.inject.Inject

class HomeDataSourceImpl
@Inject
constructor(
    private val homeService: HomeService,
) : HomeDataSource {

    override suspend fun getHomeStatus(): Pair<ContestInfoDto, List<PostInfoDto>>? {
        val response = safeAPICall {
            homeService.getHomeStatus()
        }.body?.responseData

        if (response == null) {
            return response
        }

        return response.contestInfo.toContestInfoDto() to response.postInfo.map { it.toPostInfoDto() }
    }
}
