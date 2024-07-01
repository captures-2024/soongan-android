package com.captures2024.soongan.core.data.service

import com.captures2024.soongan.core.model.network.request.SignWithTokenRequest
import com.captures2024.soongan.core.model.network.response.SignWithTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

interface MembersService {

//    @Headers("Authorization: true")
    @POST("members/login")
    suspend fun signWithToken(
        @Body request: SignWithTokenRequest
    ): Response<SignWithTokenResponse>

}