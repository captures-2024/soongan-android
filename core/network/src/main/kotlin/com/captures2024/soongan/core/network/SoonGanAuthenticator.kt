package com.captures2024.soongan.core.network

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.datastore.TokenDataSource
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class SoonGanAuthenticator
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val tokenDataSource: TokenDataSource,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.request.header("Authorization") == null) {
            return null
        }

        if (response.code == 401) {
//            val refreshToken = runBlocking { tokenDataSource.getRefreshToken() }
//
//            val reissueResult = runCatching {
//                runBlocking {
//                    val result = reissueTokenUseCase().getOrThrow()
//
//                    return@runBlocking if (result) {
//                        tokenDataSource.getAccessToken()
//                    } else {
//                        tokenDataSource.getAccessToken()
//                    }
//                }
//            }.onFailure {
//                runBlocking {
//                    tokenDataSource.clearAllToken()
//                }
//            }.getOrThrow()

            return response.request.newBuilder()
//                .header("Authorization", "Bearer ${newTokens.data.accessToken}")
                .build()
        }
        return null
    }
}
