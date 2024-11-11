package com.captures2024.soongan.core.data.remote

import com.captures2024.soongan.core.model.network.SocialSignType
import com.captures2024.soongan.core.model.network.response.members.ReissueTokenResponse
import com.captures2024.soongan.core.model.network.response.members.SignInWithTokenResponse

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