package com.captures2024.soongan.presentation.viewmodel.main.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import com.captures2024.soongan.presentation.viewmodel.model.ProfileBottomSheetMenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileBottomSheetViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ProfileBottomSheetViewModel.State, ProfileBottomSheetViewModel.Effect, ProfileBottomSheetViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {
    data object State : UIState

    sealed interface Effect : UISideEffect {

        data object NavigateToEdit : Effect

        data object NavigateToPush : Effect

        data object NavigateToTerms : Effect

        data object NavigateToFaq : Effect

        data object NavigateToWithdraw : Effect

        data object NavigateToSignOut : Effect
    }

    sealed interface Intent : UIIntent {
        data class OnClickMenuItem(
            val item: ProfileBottomSheetMenuItem,
        ) : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickMenuItem -> handleOnClickMenuItem(intent)
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private fun handleOnClickMenuItem(intent: Intent.OnClickMenuItem) {
        postSideEffect(
            sideEffect = when (intent.item) {
                ProfileBottomSheetMenuItem.EDIT -> Effect.NavigateToEdit
                ProfileBottomSheetMenuItem.PUSH -> Effect.NavigateToPush
                ProfileBottomSheetMenuItem.TERMS_AND_POLICY -> Effect.NavigateToTerms
                ProfileBottomSheetMenuItem.FAQ -> Effect.NavigateToFaq
                ProfileBottomSheetMenuItem.WITHDRAW -> Effect.NavigateToWithdraw
                ProfileBottomSheetMenuItem.SIGN_OUT -> Effect.NavigateToSignOut
            },
        )
    }
}
