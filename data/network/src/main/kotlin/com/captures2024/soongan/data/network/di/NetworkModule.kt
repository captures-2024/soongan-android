package com.captures2024.soongan.data.network.di

import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.data.network.AuthInterceptor
import com.captures2024.soongan.data.network.BuildConfig
import com.captures2024.soongan.data.network.SoonGanAuthenticator
import com.captures2024.soongan.data.source.token.local.TokenLocalDataSource
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val MaxTimeoutMillis = 60_000L

@OptIn(ExperimentalSerializationApi::class)
private val jsonRule = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    prettyPrint = true
    isLenient = true
    explicitNulls = false
}

internal val jsonConverterFactory = jsonRule.asConverterFactory("application/json".toMediaType())

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providerSoonGanAuthenticator(
        authInterceptor: AuthInterceptor,
        analyticsHelper: AnalyticsHelper,
        tokenLocalDataSource: TokenLocalDataSource,
    ): Authenticator = SoonGanAuthenticator(
        authInterceptor = authInterceptor,
        analyticsHelper = analyticsHelper,
        tokenLocalDataSource = tokenLocalDataSource,
    )

    @Provides
    @Singleton
    fun providerAuthInterceptor(
        analyticsHelper: AnalyticsHelper,
        tokenLocalDataSource: TokenLocalDataSource,
    ): AuthInterceptor = AuthInterceptor(
        analyticsHelper = analyticsHelper,
        tokenLocalDataSource = tokenLocalDataSource,
    )

    @Provides
    @Singleton
    fun provideLoggingInterceptor(analyticsHelper: AnalyticsHelper): HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
        analyticsHelper.d { message }
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
//        level = when (BuildConfig.DEBUG) {
//            true -> HttpLoggingInterceptor.Level.BODY
//            false -> HttpLoggingInterceptor.Level.NONE
//        }
    }

    @Provides
    @Singleton
    fun providerHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        authenticator: Authenticator,
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(MaxTimeoutMillis, TimeUnit.MILLISECONDS)
        .connectTimeout(MaxTimeoutMillis, TimeUnit.MILLISECONDS)
        .writeTimeout(MaxTimeoutMillis, TimeUnit.MILLISECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .authenticator(authenticator)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.CAPTURES_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(jsonConverterFactory)
        .build()
}
