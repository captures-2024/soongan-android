package com.captures2024.soongan.data.repository.fcm.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import com.captures2024.soongan.data.source.fcm.local.FcmLocalDataSource
import com.captures2024.soongan.data.source.fcm.remote.FcmRemoteDataSource
import com.captures2024.soongan.domain.repository.fcm.FcmRepository
import javax.inject.Inject

class FcmRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val fcmLocalDataSource: FcmLocalDataSource,
    private val fcmRemoteDataSource: FcmRemoteDataSource,
) : FcmRepository {

    init {
        analyticsHelper.d { "FcmRepository::init" }
    }

    override suspend fun initFcm(): ResultConditionDto {
        val result = fcmRemoteDataSource.initFcm(
            fcmToken = fcmLocalDataSource.getFcm(),
        )

        return ResultConditionDto(result?.token == getFcm())
    }

    override suspend fun getFcm(): String {
        return fcmLocalDataSource.getFcm()
    }
}
