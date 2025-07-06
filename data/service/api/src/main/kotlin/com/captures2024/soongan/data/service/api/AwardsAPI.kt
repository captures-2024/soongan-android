package com.captures2024.soongan.data.service.api

import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.awards.GetAwardsDetailInfoResponse
import com.captures2024.soongan.core.model.network.response.awards.GetAwardsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AwardsAPI {

    @GET("awards")
    suspend fun getAwardsList(): Response<BaseResponse<GetAwardsListResponse>>

    @GET("awards/{contestId}")
    suspend fun getAwardsDetailInfo(
        @Path("contestId") contestId: Long,
    ): Response<BaseResponse<GetAwardsDetailInfoResponse>>
}
