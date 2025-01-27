package com.captures2024.soongan.core.viewmodel.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtCheckType
import com.captures2024.soongan.core.viewmodel.model.profile.ProfileBtmShtDepthState
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
            val outType: ProfileBottomSheetOutType,
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

    override suspend fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickMenuItem -> handleClickContentItem(intent)

            is Intent.OnBackIdle -> reduce { copy(depthStatus = ProfileBtmShtDepthState.Idle) }

            is Intent.OnCheckProcess -> onCheckProcess(intent)

            is Intent.OnDoneProcess -> outOfBottomSheet(outType = ProfileBottomSheetOutType.DONE_BUTTON)

            is Intent.OnPushSettingChanged -> onPushSettingChanged(intent)

            is Intent.OnCloseBottomSheet -> outOfBottomSheet(outType = ProfileBottomSheetOutType.OUT_OF_AREA)
        }
    }

    private fun handleClickContentItem(intent: Intent.OnClickMenuItem) = launch {
        when (intent.item) {
            ProfileBtmShtMenuItem.EDIT -> outOfBottomSheet(outType = ProfileBottomSheetOutType.EDIT)

            ProfileBtmShtMenuItem.FAQ -> outOfBottomSheet(outType = ProfileBottomSheetOutType.FAQ)

            ProfileBtmShtMenuItem.PUSH -> reduce { copy(depthStatus = ProfileBtmShtDepthState.Push) }

            ProfileBtmShtMenuItem.TERMS_AND_POLICY -> outOfBottomSheet(outType = ProfileBottomSheetOutType.TERMS_AND_POLICY)

            ProfileBtmShtMenuItem.SIGN_OUT -> reduce { copy(depthStatus = ProfileBtmShtDepthState.SignOut.Check) }

            ProfileBtmShtMenuItem.WITHDRAW -> reduce { copy(depthStatus = ProfileBtmShtDepthState.Withdraw.Check) }
        }
    }

    private fun onCheckProcess(intent: Intent.OnCheckProcess) {
        when (intent.type) {
            ProfileBtmShtCheckType.SIGN_OUT -> {
                // 로그 아웃 useCase
//                if(result == null)
//                    reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Error) }


                reduce { copy(depthStatus = ProfileBtmShtDepthState.SignOut.Done) }
            }

            ProfileBtmShtCheckType.WITHDRAW -> {
                // 회원 탈퇴 useCase
//                if(result == null)
//                    reduce { copy(depthStatus = ProfileBtmShtDepthStatus.Error) }


                reduce { copy(depthStatus = ProfileBtmShtDepthState.Withdraw.Done) }
            }
        }
    }

    private fun onPushSettingChanged(intent: Intent.OnPushSettingChanged) {
        val pushSettings = currentState.pushSettings

        reduce {
            copy(pushSettings = pushSettings.switch(intent.type))
        }
    }

    private fun outOfBottomSheet(outType: ProfileBottomSheetOutType) {
        when (outType) {
            ProfileBottomSheetOutType.EDIT ->
                postSideEffect(Effect.OutOfBottomSheet(outType = ProfileBottomSheetOutType.EDIT))

            ProfileBottomSheetOutType.FAQ ->
                postSideEffect(Effect.OutOfBottomSheet(outType = ProfileBottomSheetOutType.FAQ))

            ProfileBottomSheetOutType.TERMS_AND_POLICY ->
                postSideEffect(Effect.OutOfBottomSheet(outType = ProfileBottomSheetOutType.TERMS_AND_POLICY))

            ProfileBottomSheetOutType.DONE_BUTTON ->
                postSideEffect(Effect.OutOfBottomSheet(outType = ProfileBottomSheetOutType.DONE_BUTTON))

            ProfileBottomSheetOutType.OUT_OF_AREA ->
                postSideEffect(Effect.OutOfBottomSheet(outType = ProfileBottomSheetOutType.OUT_OF_AREA))
        }

        reduce {
            copy(
                depthStatus = ProfileBtmShtDepthState.Idle
            )
        }
    }
}

enum class ProfileBottomSheetOutType {
    EDIT, FAQ, TERMS_AND_POLICY, DONE_BUTTON, OUT_OF_AREA
}
