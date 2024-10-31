package com.captures2024.soongan.core.android.di

import com.captures2024.soongan.core.android.helper.impl.ConnectivityManagerNetworkMonitor
import com.captures2024.soongan.core.android.helper.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkAnalyticsModule {
    @Binds
    internal abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor,
    ): NetworkMonitor
}
