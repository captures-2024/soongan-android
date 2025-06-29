package com.captures2024.soongan.data.repository.module

import com.captures2024.soongan.data.repository.report.impl.ReportRepositoryImpl
import com.captures2024.soongan.domain.repository.report.ReportRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ReportRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindReportRepository(reportRepositoryImpl: ReportRepositoryImpl): ReportRepository
}
