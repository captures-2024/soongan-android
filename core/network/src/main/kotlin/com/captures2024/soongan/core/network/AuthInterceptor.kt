package com.captures2024.soongan.core.network

import com.captures2024.soongan.core.datastore.TokenDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor
@Inject
constructor(
    private val tokenDataSource: TokenDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val defaultRequest = request()

        return@with when (shouldRequestAuthenticatedHeaders(defaultRequest.url.encodedPath)) {
            true -> {
                val accessToken = runBlocking { tokenDataSource.getAccessToken() }

                val headerRequest = defaultRequest.newBuilder()
                    .addHeader("Authorization", "Bearer $accessToken")
                    .addHeader("OS", "Android")
                    .build()

                proceed(headerRequest)
            }
            false -> chain.proceed(defaultRequest)
        }
    }

    private fun shouldRequestAuthenticatedHeaders(encodedPath: String) = when (encodedPath) {
        "TODO/reissue" -> false
        else -> true
    }
}
