package com.captures2024.soongan.core.viewmodel.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.auth.SignOutSocialPlatformUseCase
import com.captures2024.soongan.core.domain.usecase.auth.WithdrawMemberUseCase
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtOutType
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtCheckType
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtDepthState
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtMenuItem
import com.captures2024.soongan.core.viewmodel.model.profile.PushSettingState
import com.captures2024.soongan.core.viewmodel.model.profile.PushSettingType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileBottomSheetViewModel
@Inject
constructor(
    private val signOutUseCase: SignOutSocialPlatformUseCase,
    private val withdrawUseCase: WithdrawMemberUseCase,
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
) : NewBaseViewModel<ProfileBottomSheetViewModel.State, ProfileBottomSheetViewModel.Effect, ProfileBottomSheetViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle
) {

    data class State(
        val isLoading: Boolean = false,
        val depthStatus: ProfileBtmShtDepthState = ProfileBtmShtDepthState.Idle,
        val pushSettings: PushSettingState = PushSettingState(),
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("depthStatus", depthStatus.toString()),
            LogElementArgument("pushSettings", pushSettings.toString()),
        )
    }

    sealed interface Effect : UISideEffect {
        data class OutOfBottomSheet(
            val outType: ProfileBtmShtOutType,
        ) : Effect
    }

    sealed interface Intent : UIIntent {
        data class OnClickMenuItem(
            val item: ProfileBtmShtMenuItem,
        ) : Intent

        data object OnBackIdle : Intent

        data class OnCheckProcess(
            val type: ProfileBtmShtCheckType,
        ) : Intent

        data object OnDoneProcess : Intent

        data class OnPushSettingChanged(
            val type: PushSettingType,
        ) : Intent

        data object OnCloseBottomSheet : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State()
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
            message = "handleClientException"
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickMenuItem -> handleOnClickMenuItem(intent)
            is Intent.OnBackIdle -> handleOnBackIdle()
            is Intent.OnCheckProcess -> loadingLaunch { handleOnCheckProcess(intent) }
            is Intent.OnDoneProcess -> handleOnDoneProcess()
            is Intent.OnPushSettingChanged -> handleOnPushSettingChanged(intent)
            is Intent.OnCloseBottomSheet -> handleOnCloseBottomSheet()
        }
    }

    private fun handleOnClickMenuItem(intent: Intent.OnClickMenuItem) {
        when (intent.item) {
            ProfileBtmShtMenuItem.EDIT -> outOfBottomSheet(outType = ProfileBtmShtOutType.EDIT)

            ProfileBtmShtMenuItem.FAQ -> outOfBottomSheet(outType = ProfileBtmShtOutType.FAQ)

            ProfileBtmShtMenuItem.PUSH -> reduce { copy(depthStatus = ProfileBtmShtDepthState.Push) }

            ProfileBtmShtMenuItem.TERMS_AND_POLICY -> outOfBottomSheet(outType = ProfileBtmShtOutType.TERMS_AND_POLICY)

            ProfileBtmShtMenuItem.SIGN_OUT -> reduce { copy(depthStatus = ProfileBtmShtDepthState.SignOut.Check) }

            ProfileBtmShtMenuItem.WITHDRAW -> reduce { copy(depthStatus = ProfileBtmShtDepthState.Withdraw.Check) }
        }
    }

    private fun handleOnBackIdle() {
        reduce { copy(depthStatus = ProfileBtmShtDepthState.Idle) }
    }

    private suspend fun handleOnCheckProcess(intent: Intent.OnCheckProcess) {
        when (intent.type) {
            ProfileBtmShtCheckType.SIGN_OUT -> {
                val result = signOutUseCase().getOrNull()
                analyticsHelper.d(message = "signOut result = $result")

                if (result != true) {
                    reduce { copy(depthStatus = ProfileBtmShtDepthState.Error) }
                    return
                }

                reduce { copy(depthStatus = ProfileBtmShtDepthState.SignOut.Done) }
            }

            ProfileBtmShtCheckType.WITHDRAW -> {
                val result = withdrawUseCase().getOrNull()
                analyticsHelper.d(message = "withDraw result = $result")

                if (result != true) {
                    reduce { copy(depthStatus = ProfileBtmShtDepthState.Error) }
                    return
                }

                reduce { copy(depthStatus = ProfileBtmShtDepthState.Withdraw.Done) }
            }
        }
    }

    private fun handleOnDoneProcess() {
        outOfBottomSheet(outType = ProfileBtmShtOutType.DONE_STATUS)
    }

    private fun handleOnPushSettingChanged(intent: Intent.OnPushSettingChanged) {
        val pushSettings = currentState.pushSettings

        reduce {
            copy(pushSettings = pushSettings.switch(intent.type))
        }
    }

    private fun handleOnCloseBottomSheet() {
        outOfBottomSheet(outType = ProfileBtmShtOutType.OUT_OF_AREA)
    }

    private fun outOfBottomSheet(outType: ProfileBtmShtOutType) {
        postSideEffect(
            Effect.OutOfBottomSheet(
                outType = when (outType) {
                    ProfileBtmShtOutType.OUT_OF_AREA -> when (currentState.depthStatus) {
                        ProfileBtmShtDepthState.SignOut.Done,
                        ProfileBtmShtDepthState.Withdraw.Done -> ProfileBtmShtOutType.DONE_STATUS

                        else -> ProfileBtmShtOutType.OUT_OF_AREA
                    }

                    else -> outType
                }
            )
        )

        reduce {
            copy(
                depthStatus = ProfileBtmShtDepthState.Idle
            )
        }
    }
}

