package com.captures2024.soongan.core.data.di

import com.captures2024.soongan.core.data.source.ui.local.DialogLocalDataSource
import com.captures2024.soongan.core.data.source.ui.local.DialogLocalDataSourceImpl
import com.captures2024.soongan.core.data.source.fcm.local.FcmLocalDataSource
import com.captures2024.soongan.core.data.source.fcm.local.FcmLocalDataSourceImpl
import com.captures2024.soongan.core.data.source.members.local.GuestLocalDataSource
import com.captures2024.soongan.core.data.source.members.local.GuestLocalDataSourceImpl
import com.captures2024.soongan.core.data.source.members.local.MemberLocalDataSource
import com.captures2024.soongan.core.data.source.members.local.MemberLocalDataSourceImpl
import com.captures2024.soongan.core.data.source.notification.local.NotificationLocalDataSource
import com.captures2024.soongan.core.data.source.notification.local.NotificationLocalDataSourceImpl
import com.captures2024.soongan.core.data.source.ui.local.LoadingLocalDataSource
import com.captures2024.soongan.core.data.source.ui.local.LoadingLocalDataSourceImpl
import com.captures2024.soongan.core.datastore.TokenDataSource
import com.captures2024.soongan.core.datastore.TokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindTokenDataSource(tokenDataSourceImpl: TokenDataSourceImpl): TokenDataSource

    @Binds
    @Singleton
    abstract fun bindFcmLocalDataSource(fcmLocalDataSourceImpl: FcmLocalDataSourceImpl): FcmLocalDataSource

    @Binds
    @Singleton
    abstract fun bindDialogLocalDataSource(dialogLocalDataSourceImpl: DialogLocalDataSourceImpl): DialogLocalDataSource

    @Binds
    @Singleton
    abstract fun bindLoadingLocalDataSource(loadingLocalDataSourceImpl: LoadingLocalDataSourceImpl): LoadingLocalDataSource

    @Binds
    @Singleton
    abstract fun bindMemberLocalDataSource(memberLocalDataSourceImpl: MemberLocalDataSourceImpl): MemberLocalDataSource

    @Binds
    @Singleton
    abstract fun bindGuestLocalDataSource(guestLocalDataSourceImpl: GuestLocalDataSourceImpl): GuestLocalDataSource

    @Binds
    @Singleton
    abstract fun bindNotificationLocalDataSource(notificationLocalDataSourceImpl: NotificationLocalDataSourceImpl): NotificationLocalDataSource
}
