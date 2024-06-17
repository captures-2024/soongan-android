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

        val isAccessToken = defaultRequest.headers["Authorization"]

        return@with when (isAccessToken) {
            "true" -> {
                val accessToken = runBlocking { tokenDataSource.getAccessToken() }

                val newRequest = defaultRequest.newBuilder()
                    .header("Authorization", "Bearer $accessToken")
                    .addHeader("OS", "Android")
                    .build()

                proceed(newRequest)
            }
            "false" -> {
                val refreshToken = runBlocking { tokenDataSource.getRefreshToken() }

                val newRequest = defaultRequest.newBuilder()
                    .header("Authorization", "Bearer $refreshToken")
                    .addHeader("OS", "Android")
                    .build()

                proceed(newRequest)
            }
            else -> {
                val newRequest = defaultRequest.newBuilder()
                    .addHeader("OS", "Android")
                    .build()

                proceed(newRequest)
            }
        }
    }

}
