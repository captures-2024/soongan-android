package com.captures2024.soongan.data.repository.awards.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.awards.AwardsDefaultDto
import com.captures2024.soongan.data.repository.awards.AwardsRepository
import com.captures2024.soongan.data.source.awards.remote.AwardsRemoteDataSource
import javax.inject.Inject

class AwardsRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val awardsRemoteDataSource: AwardsRemoteDataSource,
) : AwardsRepository {

    init {
        analyticsHelper.d { "AwardsRepositoryImpl::init" }
    }

    override suspend fun getAwardsList(): List<AwardsDefaultDto> {
        val awardsList = awardsRemoteDataSource.getAwardsList()

        return awardsList ?: throw NullPointerException("awardsList is null")
    }
}
