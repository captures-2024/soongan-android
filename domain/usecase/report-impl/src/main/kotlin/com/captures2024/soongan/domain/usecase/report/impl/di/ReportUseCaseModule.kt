package com.captures2024.soongan.domain.usecase.report.impl.di

import com.captures2024.soongan.domain.usecase.report.PostReportUseCase
import com.captures2024.soongan.domain.usecase.report.impl.PostReportUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class ReportUseCaseModule {

    @Binds
    abstract fun bindPostReportUseCase(postReportUseCaseImpl: PostReportUseCaseImpl): PostReportUseCase
}
