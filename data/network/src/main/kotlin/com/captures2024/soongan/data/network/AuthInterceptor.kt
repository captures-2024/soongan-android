package com.captures2024.soongan.data.network

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.data.datastore.TokenDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val tokenDataSource: TokenDataSource,
) : Interceptor {

    init {
        analyticsHelper.d { "AuthInterceptor::init" }
    }

    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val defaultRequest = request()

        val newRequest = defaultRequest.newBuilder().apply {
            addHeader(AppConst.Network.AGENT_HEADER, AppConst.Network.OS)
            addHeader(BuildConfig.HEADER_KEY, BuildConfig.HEADER_VALUE)
            when (defaultRequest.headers[AppConst.Network.AUTH_HEADER]) {
                "true" -> {
                    val accessToken = runBlocking { tokenDataSource.getAccessToken() }
                    header(AppConst.Network.AUTH_HEADER, "${AppConst.Network.AUTH_PREFIX} $accessToken")
                }
                "false" -> {
                    val refreshToken = runBlocking { tokenDataSource.getRefreshToken() }
                    header(AppConst.Network.AUTH_HEADER, "${AppConst.Network.AUTH_PREFIX} $refreshToken")
                }
            }
        }.build()

        analyticsHelper.d { "AuthInterceptor::intercept - newRequest.headers: ${newRequest.headers}" }

        return@with proceed(newRequest)
    }
}
