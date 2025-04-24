package com.captures2024.soongan.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.model.utils.NotificationType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val getCurrentMemberFlowUseCase: GetCurrentMemberFlowUseCase,
) : NewBaseViewModel<NotificationViewModel.State, NotificationViewModel.Effect, NotificationViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val notificationType: NotificationType?,
        val isLoggedIn: Boolean,
    ) : UIState

    sealed interface Effect : UISideEffect

    sealed interface Intent : UIIntent {
        data object Init : Intent
        data object ClearEvent : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State(
        notificationType = null,
        isLoggedIn = false,
    )

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "" }
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> handleInit()
            is Intent.ClearEvent -> handleClearEvent()
        }
    }

    private fun handleInit() {
        launch { collectCurrentMember() }
        launch { collectEvent() }
    }

    private fun handleClearEvent() {
        // TODO
    }

    private suspend fun collectEvent() {
        // TODO
    }

    private suspend fun collectCurrentMember() {
        getCurrentMemberFlowUseCase().collect { info ->
            reduce {
                copy(
                    isLoggedIn = info != null,
                )
            }
        }
    }
}
