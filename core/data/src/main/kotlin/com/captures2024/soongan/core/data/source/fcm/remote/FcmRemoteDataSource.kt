package com.captures2024.soongan.core.data.source.fcm.remote

import com.captures2024.soongan.core.model.dto.FcmDto

interface FcmRemoteDataSource {

    suspend fun initFcm(fcmToken: String): FcmDto?
}
