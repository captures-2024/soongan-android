package com.captures2024.soongan.data.service.api

import com.captures2024.soongan.core.model.network.request.fcm.InitFcmRequest
import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.fcm.InitFcmResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FcmAPI {

    @POST("fcm")
    suspend fun initFcm(@Body request: InitFcmRequest): Response<BaseResponse<InitFcmResponse>>
}
