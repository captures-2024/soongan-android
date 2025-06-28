package com.captures2024.soongan.data.source.module.report

import com.captures2024.soongan.data.source.report.impl.service.ReportService
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
