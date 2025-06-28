package com.captures2024.soongan.data.service.module

import com.captures2024.soongan.data.service.api.ReportService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ReportServiceModule {

    @Singleton
    @Provides
    fun provideReportService(retrofit: Retrofit): ReportService = retrofit.create(ReportService::class.java)
}