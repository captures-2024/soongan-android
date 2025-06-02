package com.captures2024.soongan.data.source.home.impl.service

import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.home.GetHomeStatusResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface HomeService {

    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @GET("home")
    suspend fun getHomeStatusWithToken(): Response<BaseResponse<GetHomeStatusResponse>>

    @GET("home")
    suspend fun getHomeStatusWithGuest(): Response<BaseResponse<GetHomeStatusResponse>>
}
