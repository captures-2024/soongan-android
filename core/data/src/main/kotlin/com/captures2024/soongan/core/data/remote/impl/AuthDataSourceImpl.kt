package com.captures2024.soongan.core.data.remote.impl

import com.captures2024.soongan.core.data.remote.AuthDataSource
import com.captures2024.soongan.core.data.service.AuthService
import com.captures2024.soongan.core.data.utils.safeAPICall
import com.captures2024.soongan.core.model.utils.SocialSignType
import com.captures2024.soongan.core.model.network.request.auth.ReissueTokenRequest
import com.captures2024.soongan.core.model.network.request.auth.SignWithTokenRequest
import com.captures2024.soongan.core.model.network.response.auth.ReissueTokenResponse
import com.captures2024.soongan.core.model.network.response.auth.SignInWithTokenResponse
import javax.inject.Inject

class AuthDataSourceImpl
@Inject
constructor(
    private val service: AuthService,
)  : AuthDataSource {

    override suspend fun withdrawWithToken(): Boolean {
        val result = safeAPICall { service.withdrawWithToken() }

        return when (result.body) {
            null -> false
            else -> true
        }
    }

    override suspend fun signOutWithToken(): Boolean {
        val result = safeAPICall { service.signOutWithToken() }

        return when (result.body) {
            null -> false
            else -> true
        }
    }

    override suspend fun signInWithToken(
        type: SocialSignType,
        token: String,
        fcmToken: String,
    ): SignInWithTokenResponse? = safeAPICall {
        service.signInWithToken(
            request = SignWithTokenRequest(
                provider = type.provider,
                idToken = token,
                fcmToken = fcmToken,
            ),
        )
    }.body?.responseData

    override suspend fun reissueToken(
        accessToken: String,
        refreshToken: String,
    ): ReissueTokenResponse? = safeAPICall {
        service.reissueToken(
            request = ReissueTokenRequest(
                accessToken = accessToken,
                refreshToken = refreshToken,
            ),
        )
    }.body?.responseData
}