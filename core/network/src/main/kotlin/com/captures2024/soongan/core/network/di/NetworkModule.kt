package com.captures2024.soongan.core.network.di

import com.captures2024.soongan.core.datastore.BuildConfig
import com.captures2024.soongan.core.datastore.TokenDataSource
import com.captures2024.soongan.core.network.AuthInterceptor
import com.captures2024.soongan.core.network.SoonGanAuthenticator
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val MaxTimeoutMillis = 60_000L

private val jsonRule = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    prettyPrint = true
    isLenient = true
}

private val jsonConverterFactory = jsonRule.asConverterFactory("application/json".toMediaType())

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providerSoonGanAuthenticator(tokenDataSource: TokenDataSource, ): Authenticator = SoonGanAuthenticator(tokenDataSource = tokenDataSource)

    @Provides
    @Singleton
    fun providerAuthInterceptor(tokenDataSource: TokenDataSource): AuthInterceptor = AuthInterceptor(tokenDataSource = tokenDataSource)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor { message ->
//        Timber.tag("ApiService").d(message)
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
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.CAPTURES_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(jsonConverterFactory)
        .build()

}