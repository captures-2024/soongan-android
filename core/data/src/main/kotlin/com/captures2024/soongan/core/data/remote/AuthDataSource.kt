package com.captures2024.soongan.core.data.remote

import com.captures2024.soongan.core.model.utils.SocialSignType
import com.captures2024.soongan.core.model.network.response.auth.ReissueTokenResponse
import com.captures2024.soongan.core.model.network.response.auth.SignInWithTokenResponse

interface AuthDataSource {

    suspend fun withdrawWithToken(): Boolean

    suspend fun signOutWithToken(): Boolean

    suspend fun signInWithToken(
        type: SocialSignType,
        token: String,
        fcmToken: String,
    ): SignInWithTokenResponse?

    suspend fun reissueToken(
        accessToken: String,
        refreshToken: String,
    ): ReissueTokenResponse?
}