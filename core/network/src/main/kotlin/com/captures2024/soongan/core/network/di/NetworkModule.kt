package com.captures2024.soongan.core.network.di

import com.captures2024.soongan.core.datastore.BuildConfig
import com.captures2024.soongan.core.datastore.TokenDataSource
import com.captures2024.soongan.core.network.AuthInterceptor
import com.captures2024.soongan.core.network.NullOrEmptyConverterFactory
import com.captures2024.soongan.core.network.SoonGanAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providerSoonGanAuthenticator(
        tokenDataSource: TokenDataSource,
    ): Authenticator = SoonGanAuthenticator(
        tokenDataSource = tokenDataSource,
    )

    @Provides
    @Singleton
    fun providerAuthInterceptor(
        tokenDataSource: TokenDataSource
    ): AuthInterceptor = AuthInterceptor(tokenDataSource = tokenDataSource)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun providerHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        authenticator: Authenticator,
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .authenticator(authenticator)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        nullOrEmptyConverterFactory: NullOrEmptyConverterFactory,
        scalarsConverterFactory: ScalarsConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.CAPTURES_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(nullOrEmptyConverterFactory)
        .addConverterFactory(scalarsConverterFactory)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideScalarConverterFactory(): ScalarsConverterFactory = ScalarsConverterFactory.create()

    @Provides
    @Singleton
    fun provideNullOrEmptyConverterFactory(): NullOrEmptyConverterFactory = NullOrEmptyConverterFactory()

}