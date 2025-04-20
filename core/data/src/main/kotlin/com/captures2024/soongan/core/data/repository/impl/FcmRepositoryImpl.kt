package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.source.fcm.remote.FcmRemoteDataSource
import com.captures2024.soongan.core.data.repository.FcmRepository
import com.captures2024.soongan.core.data.source.fcm.local.FcmLocalDataSource
import com.captures2024.soongan.core.model.dto.ResultConditionDto
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
        ) ?: return ResultConditionDto(false)

        if (result.token != getFcm()) {
            return ResultConditionDto(false)
        }

        return ResultConditionDto(true)
    }

    override suspend fun getFcm(): String {
        val fcmToken = fcmLocalDataSource.getFcm()
        return fcmToken
    }
}
