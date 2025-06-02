package com.captures2024.soongan.data.source.auth.impl.remote

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.network.request.auth.ReissueTokenRequest
import com.captures2024.soongan.core.model.network.request.auth.SignWithTokenRequest
import com.captures2024.soongan.core.model.network.response.auth.ReissueTokenResponse
import com.captures2024.soongan.core.model.network.response.auth.SignInWithTokenResponse
import com.captures2024.soongan.core.model.utils.SocialSignType
import com.captures2024.soongan.data.source.auth.impl.service.AuthService
import com.captures2024.soongan.data.source.auth.remote.AuthRemoteDataSource
import com.captures2024.soongan.data.source.utils.safeAPICall
import javax.inject.Inject

class AuthRemoteDataSourceImpl
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val service: AuthService,
) : AuthRemoteDataSource {

    init {
        analyticsHelper.d { "AuthRemoteDataSource::init" }
    }

    override suspend fun withdrawWithToken(): Boolean {
        analyticsHelper.d { "withdrawWithToken - entry" }

        val response = safeAPICall { service.withdrawWithToken() }

        val responseHeader = response.headers

        analyticsHelper.d { "withdrawWithToken - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "withdrawWithToken - responseBody: $responseBody" }

        return when (responseBody) {
            null -> false
            else -> true
        }
    }

    override suspend fun signOutWithToken(): Boolean {
        analyticsHelper.d { "signOutWithToken - entry" }

        val response = safeAPICall { service.signOutWithToken() }

        val responseHeader = response.headers

        analyticsHelper.d { "withdrawWithToken - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "withdrawWithToken - responseBody: $responseBody" }

        return when (responseBody) {
            null -> false
            else -> true
        }
    }

    override suspend fun signInWithToken(
        type: SocialSignType,
        token: String,
        fcmToken: String,
    ): SignInWithTokenResponse? {
        analyticsHelper.d { "signInWithToken - type: $type, token: $token, fcmToken: $fcmToken" }

        val response = safeAPICall {
            service.signInWithToken(
                request = SignWithTokenRequest(
                    provider = type.provider,
                    idToken = token,
                    fcmToken = fcmToken,
                ),
            )
        }

        val responseHeader = response.headers

        analyticsHelper.d { "signInWithToken - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "signInWithToken - responseBody: $responseBody" }

        return responseBody?.responseData
    }

    override suspend fun reissueToken(
        accessToken: String,
        refreshToken: String,
    ): ReissueTokenResponse? {
        analyticsHelper.d { "reissueToken - accessToken: $accessToken, refreshToken: $refreshToken" }

        val response = safeAPICall {
            service.reissueToken(
                request = ReissueTokenRequest(
                    accessToken = accessToken,
                    refreshToken = refreshToken,
                ),
            )
        }

        val responseHeader = response.headers

        analyticsHelper.d { "signInWithToken - responseHeader: $responseHeader" }

        val responseBody = response.body

        analyticsHelper.d { "signInWithToken - responseBody: $responseBody" }

        return responseBody?.responseData
    }
}