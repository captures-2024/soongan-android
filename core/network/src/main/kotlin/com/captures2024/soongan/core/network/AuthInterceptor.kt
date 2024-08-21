package com.captures2024.soongan.core.network

import com.captures2024.soongan.core.datastore.TokenDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class AuthInterceptor
@Inject
constructor(
    private val tokenDataSource: TokenDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val defaultRequest = request()

        val isAccessToken = defaultRequest.headers[AUTH_HEADER]

        return@with when (isAccessToken) {
            "true" -> {
                val accessToken = runBlocking { tokenDataSource.getAccessToken() }

                val newRequest = defaultRequest.newBuilder()
                    .header(AUTH_HEADER, "$AUTH_PREFIX $accessToken")
                    .addHeader(AGENT_HEADER, OS)
                    .build()

                proceed(newRequest)
            }

            "false" -> {
                val refreshToken = runBlocking { tokenDataSource.getRefreshToken() }

                val newRequest = defaultRequest.newBuilder()
                    .header(AUTH_HEADER, "$AUTH_PREFIX $refreshToken")
                    .addHeader(AGENT_HEADER, OS)
                    .build()

                proceed(newRequest)
            }

            else -> {
                val newRequest = defaultRequest.newBuilder()
                    .addHeader(AGENT_HEADER, OS)
                    .build()

                proceed(newRequest)
            }
        }
    }

    companion object {
        private const val AUTH_HEADER = "Authorization"
        private const val AUTH_PREFIX = "Bearer"
        private const val AGENT_HEADER = "User-Agent"
        private const val OS = "ANDROID"
    }
}
