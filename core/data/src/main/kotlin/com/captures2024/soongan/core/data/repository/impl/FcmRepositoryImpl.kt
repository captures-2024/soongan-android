package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.data.remote.FcmDataSource
import com.captures2024.soongan.core.data.repository.FcmRepository
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import javax.inject.Inject

class FcmRepositoryImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val fcmDataSource: FcmDataSource,
) : FcmRepository {

    init {
        analyticsHelper.d { "FcmRepository::init" }
    }

    override suspend fun initFcm(): ResultConditionDto {
        val result = fcmDataSource.initFcm() ?: return ResultConditionDto(false)

        if (result.token != getFcm()) {
            return ResultConditionDto(false)
        }

        return ResultConditionDto(true)
    }

    override suspend fun getFcm(): String = fcmDataSource.getFcm()
        .also { analyticsHelper.d { "getFcm - fcm: $it" } }
}
