package com.captures2024.soongan.core.data.remote

import com.captures2024.soongan.core.model.dto.FcmDto

interface FcmDataSource {

    suspend fun initFcm(): FcmDto?

    suspend fun getFcm(): String
}
