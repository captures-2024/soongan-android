package com.captures2024.soongan.data.repository.home.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.HomeContestInfoDto
import com.captures2024.soongan.core.model.dto.PostInfoDto
import com.captures2024.soongan.data.source.home.remote.HomeRemoteDataSource
import com.captures2024.soongan.data.source.member.local.GuestLocalDataSource
import com.captures2024.soongan.domain.repository.home.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val guestLocalDataSource: GuestLocalDataSource,
) : HomeRepository {

    init {
        analyticsHelper.d { "HomeRepository::init" }
    }

    override suspend fun getHome(): Pair<HomeContestInfoDto, List<PostInfoDto>> {
        val response = when (guestLocalDataSource.isGuestMode.value) {
            true -> homeRemoteDataSource.getHomeStatusByGuest()

            false -> homeRemoteDataSource.getHomeStatus()
        }

        return response ?: error("response is null")
    }
}
