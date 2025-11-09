package com.captures2024.soongan.data.source.module

import com.captures2024.soongan.data.source.system.impl.local.AppUpdateLocalDataSourceImpl
import com.captures2024.soongan.data.source.system.impl.local.DialogLocalDataSourceImpl
import com.captures2024.soongan.data.source.system.impl.local.InAppBrowserLocalDataSourceImpl
import com.captures2024.soongan.data.source.system.impl.local.LoadingLocalDataSourceImpl
import com.captures2024.soongan.data.source.system.impl.remote.AppVersionRemoteDataSourceImpl
import com.captures2024.soongan.data.source.system.local.AppUpdateLocalDataSource
import com.captures2024.soongan.data.source.system.local.DialogLocalDataSource
import com.captures2024.soongan.data.source.system.local.InAppBrowserLocalDataSource
import com.captures2024.soongan.data.source.system.local.LoadingLocalDataSource
import com.captures2024.soongan.data.source.system.remote.AppVersionRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SystemSourceModule {

    @Binds
    @Singleton
    abstract fun bindInAppBrowserLocalDataSource(inAppBrowserLocalDataSourceImpl: InAppBrowserLocalDataSourceImpl): InAppBrowserLocalDataSource

    @Binds
    @Singleton
    abstract fun bindDialogLocalDataSource(dialogLocalDataSourceImpl: DialogLocalDataSourceImpl): DialogLocalDataSource

    @Binds
    @Singleton
    abstract fun bindLoadingLocalDataSource(loadingLocalDataSourceImpl: LoadingLocalDataSourceImpl): LoadingLocalDataSource

    @Binds
    @Singleton
    abstract fun bindAppUpdateLocalDataSource(appUpdateLocalDataSourceImpl: AppUpdateLocalDataSourceImpl): AppUpdateLocalDataSource

    @Binds
    @Singleton
    abstract fun bindAppVersionRemoteDataSource(appVersionRemoteDataSourceImpl: AppVersionRemoteDataSourceImpl): AppVersionRemoteDataSource
}
