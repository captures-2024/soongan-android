package com.captures2024.soongan.core.network

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.datastore.TokenDataSource
import com.captures2024.soongan.core.model.network.request.auth.ReissueTokenRequest
import com.captures2024.soongan.core.network.di.jsonConverterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@Suppress("UnusedPrivateProperty")
class SoonGanAuthenticator
@Inject
constructor(
    authInterceptor: AuthInterceptor,
    private val analyticsHelper: AnalyticsHelper,
    private val tokenDataSource: TokenDataSource,
) : Authenticator {
    private val httpClient = OkHttpClient.Builder()
        .readTimeout(5_000L, TimeUnit.MILLISECONDS)
        .connectTimeout(5_000L, TimeUnit.MILLISECONDS)
        .writeTimeout(5_000L, TimeUnit.MILLISECONDS)
        .addInterceptor(authInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.CAPTURES_BASE_URL)
        .client(httpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(jsonConverterFactory)
        .build()

    private val reissueService: ReissueService = retrofit.create(ReissueService::class.java)

    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.request.header("Authorization") == null) {
            return null
        }

        if (response.code == 401) {
            analyticsHelper.d { "authenticate - response.code: ${response.code}" }

            val accessToken = runBlocking { tokenDataSource.getAccessToken() }

            if (accessToken.isEmpty()) {
                analyticsHelper.d { "authenticate - accessToken: $accessToken" }
                return null
            }

            val refreshToken = runBlocking { tokenDataSource.getRefreshToken() }

            if (refreshToken.isEmpty()) {
                analyticsHelper.d { "authenticate - refreshToken: $refreshToken" }
                return null
            }

            val reissueResponse = runCatching {
                runBlocking {
                    val response = reissueService.reissueToken(
                        request = ReissueTokenRequest(
                            accessToken = accessToken,
                            refreshToken = refreshToken,
                        ),
                    )

                    val reissueResponse = response.body()?.responseData

                    analyticsHelper.d { "authenticate - reissueResponse: $reissueResponse" }

                    if (reissueResponse == null) {
                        return@runBlocking null
                    }

                    tokenDataSource.setAccessToken(reissueResponse.accessToken)
                    tokenDataSource.setRefreshToken(reissueResponse.refreshToken)

                    return@runBlocking reissueResponse
                }
            }.getOrNull()

            if (reissueResponse == null) {
                return null
            }

            return response.request.newBuilder()
                .header("Authorization", "Bearer ${reissueResponse.accessToken}")
                .build()
        }

        return null
    }
}
