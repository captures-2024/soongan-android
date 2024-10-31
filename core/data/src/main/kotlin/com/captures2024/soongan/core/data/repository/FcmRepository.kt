package com.captures2024.soongan.core.data.repository

import com.captures2024.soongan.core.model.dto.ResultConditionDto

interface FcmRepository {

    suspend fun initFcm(fcmToken: String): ResultConditionDto
}
