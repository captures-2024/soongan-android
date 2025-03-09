package com.captures2024.soongan.core.data.service

import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.home.GetHomeStatusResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface HomeService {

    @Headers("Authorization: true")
    @GET("home")
    suspend fun getHomeStatus(): Response<BaseResponse<GetHomeStatusResponse>>
}
