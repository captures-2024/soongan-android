package com.captures2024.soongan.data.network

import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.network.request.auth.ReissueTokenRequest
import com.captures2024.soongan.core.model.network.response.BaseResponse
import com.captures2024.soongan.core.model.network.response.auth.ReissueTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PATCH

interface ReissueService {

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
