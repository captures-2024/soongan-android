package com.captures2024.soongan.core.network

import android.content.Context
import com.captures2024.soongan.core.datastore.TokenDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class SoonGanAuthenticator
@Inject
constructor(
    private val tokenDataSource: TokenDataSource,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.request.header("Authorization") == null) {
            return null
        }

        if (response.code == 401) {
            val refreshToken = runBlocking { tokenDataSource.getRefreshToken() }

            val newTokens = runCatching {
                runBlocking {
//                    api.refreshToken("Bearer $refreshToken")
                }
            }.onSuccess {
                // TODO setAccessToken, setRefreshToken
//                runBlocking {
//                    tokenDataSource.setAccessToken()
//                    tokenDataSource.setRefreshToken()
//                }
            }.onFailure {
                runBlocking {
                    tokenDataSource.clearAllToken()
                }
            }.getOrThrow()

            return response.request.newBuilder()
//                .header("Authorization", "Bearer ${newTokens.data.accessToken}")
                .build()
        }
        return null
    }
}