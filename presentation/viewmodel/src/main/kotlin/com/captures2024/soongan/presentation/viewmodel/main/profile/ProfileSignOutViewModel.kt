package com.captures2024.soongan.presentation.viewmodel.main.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.auth.SignOutSocialPlatformUseCase
import com.captures2024.soongan.core.domain.usecase.dialog.PostSingleButtonDialogUseCase
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.ClearCurrentMemberUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileSignOutViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val postSingleButtonDialogUseCase: PostSingleButtonDialogUseCase,
    private val signOutSocialPlatformUseCase: SignOutSocialPlatformUseCase,
    private val clearCurrentMemberUseCase: ClearCurrentMemberUseCase,
) : BaseViewModel<ProfileSignOutViewModel.State, ProfileSignOutViewModel.Effect, ProfileSignOutViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val isSuccess: Boolean,
    ) : UIState

    sealed interface Effect : UISideEffect {

        data object NavigateToBack : Effect

        data object NavigateToDone : Effect
    }

    sealed interface Intent : UIIntent {

        data object OnClickBack : Intent

        data object OnClickConfirmSignOut : Intent

        data object OnClickDoneSignOut : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State(
            isSuccess = false,
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnClickConfirmSignOut -> loadingLaunch { handleOnClickConfirmSignOut() }
            is Intent.OnClickDoneSignOut -> launch { handleOnClickDoneSignOut() }
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private suspend fun handleOnClickConfirmSignOut() {
        val result = signOutSocialPlatformUseCase().getOrNull()

        if (result != true) {
            postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
            return
        }

        reduce {
            copy(
                isSuccess = true,
            )
        }
    }

    private suspend fun handleOnClickDoneSignOut() {
        postSideEffect(Effect.NavigateToDone)

        clearCurrentMemberUseCase()
    }
}
