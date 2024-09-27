package com.captures2024.soongan.core.android.di

import com.captures2024.soongan.core.android.helper.AnalyticsHelper
import com.captures2024.soongan.core.android.helper.impl.NapierAnalyticsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
data object AnalyticsModule {

    @Provides
    @Singleton
    fun provideAnalyticsHelper(): AnalyticsHelper = NapierAnalyticsHelper().apply { initialize() }
}