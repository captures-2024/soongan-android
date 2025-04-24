package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.source.home.remote.HomeRemoteDataSource
import com.captures2024.soongan.core.data.repository.HomeRepository
import com.captures2024.soongan.core.model.dto.ContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import javax.inject.Inject

class HomeRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val homeRemoteDataSource: HomeRemoteDataSource,
) : HomeRepository {

    init {
        analyticsHelper.d { "HomeRepository::init" }
    }

    override suspend fun getHome(): Pair<ContestInfoDto, List<PostInfoDto>> {
        val response = homeRemoteDataSource.getHomeStatus()

        return response ?: error("response is null")
    }
}
