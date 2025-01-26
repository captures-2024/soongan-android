package com.captures2024.soongan.core.viewmodel.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtCheckType
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtDepthStatus
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtMenuItem
import com.captures2024.soongan.core.viewmodel.model.profile.PushSettingState
import com.captures2024.soongan.core.viewmodel.model.profile.PushSettingType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileBtmShtViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,

    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ProfileBtmShtViewModel.State, ProfileBtmShtViewModel.Effect, ProfileBtmShtViewModel.Intent>(
    savedStateHandle = savedStateHandle
) {

    data class State(
        val isLoading: Boolean = false,
        val depthStatus: ProfileBtmShtDepthStatus = ProfileBtmShtDepthStatus.Idle,
        val pushSettings: PushSettingState = PushSettingState(),
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("depthStatus", depthStatus.toString()),
            LogElementArgument("pushSettings", pushSettings.toString()),
        )
    }

    sealed interface Effect : UISideEffect {
        data object SendRequestNavigateToEditProfile : Effect

        data object CloseBottomSheet : Effect

        data object NavigateToHome : Effect
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

    override suspend fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickMenuItem -> handleClickContentItem(intent)

            is Intent.OnBackIdle -> reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Idle) }

            is Intent.OnCheckProcess -> onCheckProcess(intent)

            is Intent.OnDoneProcess -> postSideEffect(Effect.NavigateToHome)

            is Intent.OnPushSettingChanged -> onPushSettingChanged(intent)

            is Intent.OnCloseBottomSheet -> onCloseBottomSheet()
        }
    }

    private fun handleClickContentItem(intent: Intent.OnClickMenuItem) {
        when (intent.item) {
            ProfileBtmShtMenuItem.EDIT -> postSideEffect(Effect.SendRequestNavigateToEditProfile)

            ProfileBtmShtMenuItem.FAQ -> TODO()

            ProfileBtmShtMenuItem.PUSH -> reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Push) }

            ProfileBtmShtMenuItem.TERMS_AND_POLICY -> TODO()

            ProfileBtmShtMenuItem.SIGN_OUT -> reduce { copy(depthStatus = ProfileBtmShtDepthStatus.SignOut.Check) }

            ProfileBtmShtMenuItem.WITHDRAW -> reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Withdraw.Check) }
        }
    }

    private fun onCheckProcess(intent: Intent.OnCheckProcess) {
        when (intent.type) {
            ProfileBtmShtCheckType.SIGN_OUT -> {
                // 로그아웃 useCase
//                if(result == null)
//                    reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Error) }


                reduce { copy(depthStatus = ProfileBtmShtDepthStatus.SignOut.Done) }
            }

            ProfileBtmShtCheckType.WITHDRAW -> {
                // 회원탈퇴 useCase
//                if(result == null)
//                    reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Error) }


                reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Withdraw.Done) }
            }
        }
    }

    private fun onPushSettingChanged(intent: Intent.OnPushSettingChanged) {
        val pushSettings = currentState.pushSettings

        reduce {
            copy(pushSettings = pushSettings.switch(intent.type))
        }
    }

    private fun onCloseBottomSheet() {
        postSideEffect(Effect.CloseBottomSheet)

        reduce {
            copy(
                depthStatus = ProfileBtmShtDepthStatus.Idle
            )
        }
    }
}