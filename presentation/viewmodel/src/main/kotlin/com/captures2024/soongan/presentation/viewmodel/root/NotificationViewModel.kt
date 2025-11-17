package com.captures2024.soongan.presentation.viewmodel.root

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.core.model.dto.fcm.CloudMessage
import com.captures2024.soongan.domain.usecase.member.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.notification.ClearCloudMessageEventUseCase
import com.captures2024.soongan.domain.usecase.notification.ClearNotificationEventUseCase
import com.captures2024.soongan.domain.usecase.notification.GetCloudMessageEventFlowUseCase
import com.captures2024.soongan.domain.usecase.notification.GetNotificationEventFlowUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel
@Inject
constructor(
    private val getNotificationEventFlowUseCase: GetNotificationEventFlowUseCase,
    private val getCloudMessageEventFlowUseCase: GetCloudMessageEventFlowUseCase,
    private val getCurrentMemberFlowUseCase: GetCurrentMemberFlowUseCase,
    private val clearNotificationEventUseCase: ClearNotificationEventUseCase,
    private val clearCloudMessageEventUseCase: ClearCloudMessageEventUseCase,

    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<NotificationViewModel.State, NotificationViewModel.Effect, NotificationViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val notification: NotificationDto? = null,
        val cloudMessage: CloudMessage? = null,
        val isLoggedIn: Boolean = false,
    ) : UIState

    sealed interface Effect : UISideEffect

    sealed interface Intent : UIIntent {
        data object Init : Intent

        data object ClearNotification : Intent

        data object ClearCloudMessage : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State()
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "createInitialState - currentState: $currentState" }
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> handleInit()
            is Intent.ClearNotification -> handleClearNotification()
            is Intent.ClearCloudMessage -> handleClearCloudMessage()
        }
    }

    private fun handleInit() {
        launch { collectCurrentMember() }
        launch { collectNotificationMessage() }
        launch { collectCloudMessage() }
    }

    private fun handleClearNotification() {
        clearNotificationEventUseCase()
    }

    private fun handleClearCloudMessage() {
        clearCloudMessageEventUseCase()
    }

    private suspend fun collectCurrentMember() {
        getCurrentMemberFlowUseCase().collect {
            reduce {
                copy(
                    isLoggedIn = it != null,
                )
            }
        }
    }

    private suspend fun collectNotificationMessage() {
        getNotificationEventFlowUseCase().collect {
            reduce {
                copy(
                    notification = it,
                )
            }
        }
    }

    private suspend fun collectCloudMessage() {
        getCloudMessageEventFlowUseCase().collect {
            reduce {
                copy(
                    cloudMessage = it,
                )
            }
        }
    }
}
