package com.captures2024.soongan.data.repository.fcm

import com.captures2024.soongan.core.model.dto.ResultConditionDto

interface FcmRepository {

    suspend fun initFcm(): ResultConditionDto

    suspend fun getFcm(): String
}
