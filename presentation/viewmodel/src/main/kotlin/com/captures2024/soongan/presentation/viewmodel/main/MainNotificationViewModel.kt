package com.captures2024.soongan.presentation.viewmodel.main

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.notification.GetIsNotReadNotificationCacheFlowUseCase
import com.captures2024.soongan.domain.usecase.notification.GetUnreadNotificationsCountUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainNotificationViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val isNotReadNotificationCacheFlowUseCase: GetIsNotReadNotificationCacheFlowUseCase,
    private val getUnreadNotificationsCountUseCase: GetUnreadNotificationsCountUseCase,
) : BaseViewModel<MainNotificationViewModel.State, MainNotificationViewModel.Effect, MainNotificationViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val isNotReadNotification: Boolean = false,
    ) : UIState

    sealed interface Effect : UISideEffect

    sealed interface Intent : UIIntent {
        data object Init : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State()

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> launch { handleInit() }
        }
    }

    init {
        intent(Intent.Init)
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable) { "state: $currentState" }
    }

    private suspend fun handleInit() {
        collectIsNotReadNotificationFlow()

        getUnreadNotificationsCountUseCase().getOrNull()
    }

    private fun collectIsNotReadNotificationFlow() = launch {
        isNotReadNotificationCacheFlowUseCase().collect {
            reduce {
                copy(
                    isNotReadNotification = it,
                )
            }
        }
    }
}
