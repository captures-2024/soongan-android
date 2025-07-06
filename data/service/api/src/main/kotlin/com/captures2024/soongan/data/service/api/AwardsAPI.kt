package com.captures2024.soongan.data.service.api

import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.awards.GetAwardsListResponse
import retrofit2.Response
import retrofit2.http.GET

interface AwardsAPI {

    @GET("awards")
    suspend fun getAwardsList(): Response<BaseResponse<GetAwardsListResponse>>
}
