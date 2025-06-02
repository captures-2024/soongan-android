package com.captures2024.soongan.presentation.viewmodel.main.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import com.captures2024.soongan.presentation.viewmodel.model.NotificationSettingType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileNotificationSettingViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ProfileNotificationSettingViewModel.State, ProfileNotificationSettingViewModel.Effect, ProfileNotificationSettingViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val notificationSettingState: NotificationSettingState,
    ) : UIState {

        data class NotificationSettingState(
            val all: Boolean = false,
            val contest: Boolean = false,
            val activity: Boolean = false,
            val notice: Boolean = false,
        ) {
            fun getStateByType(type: NotificationSettingType): Boolean = when (type) {
                NotificationSettingType.ALL -> all
                NotificationSettingType.CONTEST -> contest
                NotificationSettingType.ACTIVITY -> activity
                NotificationSettingType.NOTICE -> notice
            }

            fun switch(type: NotificationSettingType): NotificationSettingState = when (type) {
                NotificationSettingType.ALL -> copy(all = !all)
                NotificationSettingType.CONTEST -> copy(contest = !contest)
                NotificationSettingType.ACTIVITY -> copy(activity = !activity)
                NotificationSettingType.NOTICE -> copy(notice = !notice)
            }
        }
    }

    sealed interface Effect : UISideEffect {
        data object NavigateToBack : Effect
    }

    sealed interface Intent : UIIntent {
        data object OnClickBack : Intent

        data class OnSwitchNotificationSettingType(
            val type: NotificationSettingType,
        ) : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State(
            notificationSettingState = State.NotificationSettingState(),
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnSwitchNotificationSettingType -> handleOnSwitchNotificationSettingType(intent)
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnSwitchNotificationSettingType(intent: Intent.OnSwitchNotificationSettingType) {
        val notificationSettingState = currentState.notificationSettingState

        reduce {
            copy(
                notificationSettingState = notificationSettingState.switch(intent.type),
            )
        }
    }
}
