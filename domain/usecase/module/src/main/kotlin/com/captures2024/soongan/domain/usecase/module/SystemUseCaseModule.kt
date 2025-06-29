package com.captures2024.soongan.domain.usecase.module

import com.captures2024.soongan.domain.usecase.system.dialog.GetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.GetSingleButtonDialogEventUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.PostSingleButtonDialogUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.impl.dialog.GetIsShowGuestModeDialogFlowUseCaseImpl
import com.captures2024.soongan.domain.usecase.system.impl.dialog.GetSingleButtonDialogEventUseCaseImpl
import com.captures2024.soongan.domain.usecase.system.impl.dialog.PostSingleButtonDialogUseCaseImpl
import com.captures2024.soongan.domain.usecase.system.impl.dialog.SetIsShowGuestModeDialogFlowUseCaseImpl
import com.captures2024.soongan.domain.usecase.system.impl.inapp.GetInAppBrowserUrlFlowUseCaseImpl
import com.captures2024.soongan.domain.usecase.system.impl.inapp.LaunchInquiryUseCaseImpl
import com.captures2024.soongan.domain.usecase.system.impl.inapp.LaunchPrivacyPolicyUseCaseImpl
import com.captures2024.soongan.domain.usecase.system.impl.inapp.LaunchTermsUseCaseImpl
import com.captures2024.soongan.domain.usecase.system.impl.loading.ClearLoadingUseCaseImpl
import com.captures2024.soongan.domain.usecase.system.impl.loading.GetLoadingFlowUseCaseImpl
import com.captures2024.soongan.domain.usecase.system.impl.loading.HideLoadingUseCaseImpl
import com.captures2024.soongan.domain.usecase.system.impl.loading.IsLoadingUseCaseImpl
import com.captures2024.soongan.domain.usecase.system.impl.loading.ShowLoadingUseCaseImpl
import com.captures2024.soongan.domain.usecase.system.inapp.GetInAppBrowserUrlFlowUseCase
import com.captures2024.soongan.domain.usecase.system.inapp.LaunchInquiryUseCase
import com.captures2024.soongan.domain.usecase.system.inapp.LaunchPrivacyPolicyUseCase
import com.captures2024.soongan.domain.usecase.system.inapp.LaunchTermsUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.GetLoadingFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.IsLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class SystemUseCaseModule {

    @Binds
    abstract fun bindGetIsShowGuestModeDialogFlowUseCase(getIsShowGuestModeDialogFlowUseCaseImpl: GetIsShowGuestModeDialogFlowUseCaseImpl): GetIsShowGuestModeDialogFlowUseCase

    @Binds
    abstract fun bindGetSingleButtonDialogEventUseCase(getSingleButtonDialogEventUseCaseImpl: GetSingleButtonDialogEventUseCaseImpl): GetSingleButtonDialogEventUseCase

    @Binds
    abstract fun bindPostSingleButtonDialogUseCase(postSingleButtonDialogUseCaseImpl: PostSingleButtonDialogUseCaseImpl): PostSingleButtonDialogUseCase

    @Binds
    abstract fun bindSetIsShowGuestModeDialogFlowUseCase(setIsShowGuestModeDialogFlowUseCaseImpl: SetIsShowGuestModeDialogFlowUseCaseImpl): SetIsShowGuestModeDialogFlowUseCase

    @Binds
    abstract fun bindGetInAppBrowserUrlFlowUseCase(getInAppBrowserUrlFlowUseCaseImpl: GetInAppBrowserUrlFlowUseCaseImpl): GetInAppBrowserUrlFlowUseCase

    @Binds
    abstract fun bindLaunchPrivacyPolicyUseCase(launchPrivacyPolicyUseCaseImpl: LaunchPrivacyPolicyUseCaseImpl): LaunchPrivacyPolicyUseCase

    @Binds
    abstract fun bindLaunchTermsUseCase(launchTermsUseCaseImpl: LaunchTermsUseCaseImpl): LaunchTermsUseCase

    @Binds
    abstract fun bindLaunchInquiryUseCase(launchInquiryUseCaseImpl: LaunchInquiryUseCaseImpl): LaunchInquiryUseCase

    @Binds
    abstract fun bindClearLoadingUseCase(clearLoadingUseCaseImpl: ClearLoadingUseCaseImpl): ClearLoadingUseCase

    @Binds
    abstract fun bindGetLoadingFlowUseCase(getLoadingFlowUseCaseImpl: GetLoadingFlowUseCaseImpl): GetLoadingFlowUseCase

    @Binds
    abstract fun bindHideLoadingUseCase(hideLoadingUseCaseImpl: HideLoadingUseCaseImpl): HideLoadingUseCase

    @Binds
    abstract fun bindIsLoadingUseCase(isLoadingUseCaseImpl: IsLoadingUseCaseImpl): IsLoadingUseCase

    @Binds
    abstract fun bindShowLoadingUseCase(showLoadingUseCaseImpl: ShowLoadingUseCaseImpl): ShowLoadingUseCase
}
