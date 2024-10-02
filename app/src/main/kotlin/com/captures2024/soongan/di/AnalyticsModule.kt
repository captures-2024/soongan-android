package com.captures2024.soongan.di

import com.captures2024.soongan.core.analytics.NapierAnalyticsHelper
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {
    @Provides
    @Singleton
    fun provideNapierAnalyticsHelper(): NapierAnalyticsHelper = NapierAnalyticsHelper()

    @Provides
    @Singleton
    fun provideAnalyticsHelper(napierAnalyticsHelper: NapierAnalyticsHelper): AnalyticsHelper = napierAnalyticsHelper.apply { initialize() }
}
