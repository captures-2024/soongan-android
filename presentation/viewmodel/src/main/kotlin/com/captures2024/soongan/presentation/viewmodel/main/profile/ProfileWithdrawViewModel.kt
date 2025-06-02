package com.captures2024.soongan.presentation.viewmodel.main.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.domain.usecase.auth.WithdrawMemberUseCase
import com.captures2024.soongan.domain.usecase.member.ClearCurrentMemberUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.PostSingleButtonDialogUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileWithdrawViewModel
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
    private val withdrawMemberUseCase: WithdrawMemberUseCase,
    private val clearCurrentMemberUseCase: ClearCurrentMemberUseCase,
) : BaseViewModel<ProfileWithdrawViewModel.State, ProfileWithdrawViewModel.Effect, ProfileWithdrawViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val input: String,
        val isSuccessWithdraw: Boolean,
    ) : UIState

    sealed interface Effect : UISideEffect {

        data object NavigateToBack : Effect

        data object NavigateToDone : Effect
    }

    sealed interface Intent : UIIntent {

        data object OnClickBack : Intent

        data class OnInputValueChanged(
            val newValue: String,
        ) : Intent

        data object OnClickConfirmWithdraw : Intent

        data object OnClickDoneWithdraw : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State(
            input = AppConst.EMPTY_STRING,
            isSuccessWithdraw = false,
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnInputValueChanged -> handleOnInputValueChanged(intent)
            is Intent.OnClickConfirmWithdraw -> loadingLaunch { handleOnClickConfirmWithdraw() }
            is Intent.OnClickDoneWithdraw -> launch { handleOnClickDoneWithdraw() }
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnInputValueChanged(intent: Intent.OnInputValueChanged) {
        reduce {
            copy(
                input = intent.newValue,
            )
        }
    }

    private suspend fun handleOnClickConfirmWithdraw() {
        val result = withdrawMemberUseCase().getOrNull()

        if (result != true) {
            postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
            return
        }

        reduce {
            copy(
                isSuccessWithdraw = true,
            )
        }
    }

    private suspend fun handleOnClickDoneWithdraw() {
        postSideEffect(Effect.NavigateToDone)
        clearCurrentMemberUseCase()
    }
}
