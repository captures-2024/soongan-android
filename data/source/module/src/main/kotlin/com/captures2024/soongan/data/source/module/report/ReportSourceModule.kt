package com.captures2024.soongan.data.source.module.report

import com.captures2024.soongan.data.source.report.impl.remote.ReportRemoteDataSourceImpl
import com.captures2024.soongan.data.source.report.remote.ReportRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ReportSourceModule {

    @Binds
    @Singleton
    abstract fun bindReportRemoteDataSource(reportRemoteDataSourceImpl: ReportRemoteDataSourceImpl): ReportRemoteDataSource
}
