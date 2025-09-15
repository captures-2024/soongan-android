package com.captures2024.soongan.domain.usecase.module

import com.captures2024.soongan.domain.usecase.notification.DeleteNotificationUseCase
import com.captures2024.soongan.domain.usecase.notification.EmitNotificationUseCase
import com.captures2024.soongan.domain.usecase.notification.GetCloudMessageEventFlowUseCase
import com.captures2024.soongan.domain.usecase.notification.GetIsNotReadNotificationCacheFlowUseCase
import com.captures2024.soongan.domain.usecase.notification.GetNotificationEventFlowUseCase
import com.captures2024.soongan.domain.usecase.notification.GetNotificationSettingsUseCase
import com.captures2024.soongan.domain.usecase.notification.GetUnreadNotificationsCountUseCase
import com.captures2024.soongan.domain.usecase.notification.GetNotificationsUseCase
import com.captures2024.soongan.domain.usecase.notification.PatchNotificationSettingsUseCase
import com.captures2024.soongan.domain.usecase.notification.PostNotificationReadUseCase
import com.captures2024.soongan.domain.usecase.notification.impl.DeleteNotificationUseCaseImpl
import com.captures2024.soongan.domain.usecase.notification.impl.EmitNotificationUseCaseImpl
import com.captures2024.soongan.domain.usecase.notification.impl.GetCloudMessageEventFlowUseCaseImpl
import com.captures2024.soongan.domain.usecase.notification.impl.GetIsNotReadNotificationCacheFlowUseCaseImpl
import com.captures2024.soongan.domain.usecase.notification.impl.GetNotificationEventFlowUseCaseImpl
import com.captures2024.soongan.domain.usecase.notification.impl.GetNotificationSettingsUseCaseImpl
import com.captures2024.soongan.domain.usecase.notification.impl.GetUnreadNotificationsCountUseCaseImpl
import com.captures2024.soongan.domain.usecase.notification.impl.GetNotificationsUseCaseImpl
import com.captures2024.soongan.domain.usecase.notification.impl.PatchNotificationSettingsUseCaseImpl
import com.captures2024.soongan.domain.usecase.notification.impl.PostNotificationReadUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class NotificationUseCaseModule {

    @Binds
    abstract fun bindDeleteNotificationUseCase(deleteNotificationUseCaseImpl: DeleteNotificationUseCaseImpl): DeleteNotificationUseCase

    @Binds
    abstract fun bindEmitNotificationUseCase(emitNotificationUseCaseImpl: EmitNotificationUseCaseImpl): EmitNotificationUseCase

    @Binds
    abstract fun bindGetUnreadNotificationsCountUseCase(getUnreadNotificationsCountUseCaseImpl: GetUnreadNotificationsCountUseCaseImpl): GetUnreadNotificationsCountUseCase

    @Binds
    abstract fun bindGetNotificationsUseCase(getNotificationsUseCaseImpl: GetNotificationsUseCaseImpl): GetNotificationsUseCase

    @Binds
    abstract fun bindPostNotificationReadUseCase(postNotificationReadUseCaseImpl: PostNotificationReadUseCaseImpl): PostNotificationReadUseCase

    @Binds
    abstract fun bindGetNotificationSettingsUseCase(getNotificationSettingsUseCaseImpl: GetNotificationSettingsUseCaseImpl): GetNotificationSettingsUseCase

    @Binds
    abstract fun bindPatchNotificationSettingsUseCase(patchNotificationSettingsUseCaseImpl: PatchNotificationSettingsUseCaseImpl): PatchNotificationSettingsUseCase

    @Binds
    abstract fun bindGetIsNotReadNotificationCacheFlowUseCase(getIsNotReadNotificationCacheFlowUseCaseImpl: GetIsNotReadNotificationCacheFlowUseCaseImpl): GetIsNotReadNotificationCacheFlowUseCase

    @Binds
    abstract fun bindGetCloudMessageEventFlowUseCase(getCloudMessageEventFlowUseCaseImpl: GetCloudMessageEventFlowUseCaseImpl): GetCloudMessageEventFlowUseCase

    @Binds
    abstract fun bindGetNotificationEventFlowUseCase(getNotificationEventFlowUseCaseImpl: GetNotificationEventFlowUseCaseImpl): GetNotificationEventFlowUseCase
}
