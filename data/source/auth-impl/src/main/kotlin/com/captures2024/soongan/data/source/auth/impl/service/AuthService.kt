package com.captures2024.soongan.data.source.auth.impl.service

import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.network.request.auth.ReissueTokenRequest
import com.captures2024.soongan.core.model.network.request.auth.SignWithTokenRequest
import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.auth.ReissueTokenResponse
import com.captures2024.soongan.core.model.network.response.auth.SignInWithTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthService {

    /**
     * 회원 탈퇴 API
     *
     * 회원을 탈퇴합니다.
     **/
    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @POST("auth/withdraw")
    suspend fun withdrawWithToken(): Response<Unit>

    /**
     * 로그아웃 API
     *
     * 로그인시 발급한 JWT를 말소합니다.
     **/
    @Headers(AppConst.Network.ACCESS_TOKEN_ALLOW)
    @POST("auth/logout")
    suspend fun signOutWithToken(): Response<Unit>

    /**
     * 로그인 API
     *
     * idToken을 이용하여 로그인을 수행하고, JWT를 발급합니다.
     **/
    @POST("auth/login")
    suspend fun signInWithToken(
        @Body request: SignWithTokenRequest,
    ): Response<BaseResponse<SignInWithTokenResponse>>

    /**
     * JWT 갱신 API
     *
     * Refresh Token을 이용하여 JWT를 갱신합니다.
     **/
    @Headers(AppConst.Network.REFRESH_TOKEN_ALLOW)
    @PATCH("auth/refresh")
    suspend fun reissueToken(
        @Body request: ReissueTokenRequest,
    ): Response<BaseResponse<ReissueTokenResponse>>
}
