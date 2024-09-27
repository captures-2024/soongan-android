package com.captures2024.soongan.core.data.repository.impl

import com.captures2024.soongan.core.data.remote.FcmDataSource
import com.captures2024.soongan.core.data.repository.FcmRepository
import com.captures2024.soongan.core.model.dto.ResultConditionDto
import javax.inject.Inject

class FcmRepositoryImpl
@Inject
constructor(
    private val fcmDataSource: FcmDataSource,
) : FcmRepository {

    override suspend fun initFcm(fcmToken: String): ResultConditionDto {
        val result = fcmDataSource.initFcm(fcmToken) ?: return ResultConditionDto(false)

        return when (fcmToken) {
            result.token -> ResultConditionDto(true)
            else -> ResultConditionDto(false)
        }
    }
}
