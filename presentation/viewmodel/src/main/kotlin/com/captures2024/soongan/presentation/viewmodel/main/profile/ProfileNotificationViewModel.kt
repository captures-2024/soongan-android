package com.captures2024.soongan.presentation.viewmodel.main.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.PostSingleButtonDialogUseCase
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.domain.usecase.notifications.DeleteNotificationUseCase
import com.captures2024.soongan.core.domain.usecase.notifications.GetNotificationsCountUseCase
import com.captures2024.soongan.core.domain.usecase.notifications.GetNotificationsUseCase
import com.captures2024.soongan.core.domain.usecase.notifications.PostNotificationReadUseCase
import com.captures2024.soongan.core.model.dto.NotificationDto
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.core.model.utils.NotificationsCountTable
import com.captures2024.soongan.core.model.utils.NotificationsTable
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.EnumMap
import javax.inject.Inject

@HiltViewModel
class ProfileNotificationViewModel
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
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val getNotificationsCountUseCase: GetNotificationsCountUseCase,
    private val postNotificationReadUseCase: PostNotificationReadUseCase,
    private val deleteNotificationUseCase: DeleteNotificationUseCase,
) : BaseViewModel<ProfileNotificationViewModel.State, ProfileNotificationViewModel.Effect, ProfileNotificationViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {
    data class State(
        val notificationCategories: List<NotificationType>,
        val selectedNotificationCategory: NotificationType,
        val notifications: NotificationsTable,
        val notificationsCount: NotificationsCountTable,
    ) : UIState

    sealed interface Effect : UISideEffect {

        data object NavigateToBack : Effect

        data class NavigateFromNotification(
            val subType: NotificationSubType,
            val url: String?, // redirectUrl
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object OnClickBack : Intent

        data class OnClickCategory(
            val type: NotificationType,
        ) : Intent

        data class OnClickNotification(
            val key: Int,
        ) : Intent

        data class OnDeleteNotification(
            val key: Int,
        ) : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State(
            notificationCategories = NotificationType.entries,
            selectedNotificationCategory = NotificationType.CONTEST,
            notifications = emptyMap(),
            notificationsCount = emptyMap(),
        )
    }

    init {
        intent(Intent.Init)
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnClickCategory -> handleOnClickCategory(intent)
            is Intent.OnClickNotification -> loadingLaunch { handleOnClickNotification(intent) }
            is Intent.OnDeleteNotification -> loadingLaunch { handleOnDeleteNotification(intent) }
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private suspend fun handleInit() {
        initSetNotificationsCount()
        initSetNotifications()
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnClickCategory(intent: Intent.OnClickCategory) {
        reduce {
            copy(
                selectedNotificationCategory = intent.type,
            )
        }
    }

    private suspend fun handleOnClickNotification(intent: Intent.OnClickNotification) {
        val state = currentState
        val targetNotificationMap = state.notifications[state.selectedNotificationCategory] ?: return
        val targetNotification = targetNotificationMap[intent.key] ?: return

        val result = postNotificationReadUseCase(notificationId = targetNotification.id).getOrNull()

        if (result == null) {
            postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
            return
        }

        if (result) {
            val updatedMap = targetNotificationMap.toMutableMap()
                .apply {
                    put(
                        intent.key,
                        targetNotification.copy(
                            isRead = true,
                        ),
                    )
                }
                .toMap()

            reduce {
                copy(
                    notifications = notifications.toMutableMap()
                        .apply {
                            put(
                                state.selectedNotificationCategory,
                                updatedMap,
                            )
                        }
                        .toMap(),
                )
            }

            postSideEffect(
                Effect.NavigateFromNotification(
                    subType = targetNotification.subType,
                    url = targetNotification.redirectUrl,
                ),
            )
        }
    }

    private suspend fun handleOnDeleteNotification(intent: Intent.OnDeleteNotification) {
        val state = currentState
        val targetNotificationMap = state.notifications[state.selectedNotificationCategory] ?: return
        val targetNotification = targetNotificationMap[intent.key] ?: return

        val result = deleteNotificationUseCase(notificationId = targetNotification.id).getOrNull()

        if (result == null) {
            postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
            return
        }

        val updatedMap = targetNotificationMap.toMutableMap().apply {
            remove(intent.key)
        }.toMap()

        reduce {
            copy(
                notifications = state.notifications.toMutableMap().apply {
                    put(state.selectedNotificationCategory, updatedMap)
                }.toMap(),
            )
        }
    }

    private suspend fun initSetNotificationsCount() {
        val notificationsCountDto = getNotificationsCountUseCase().getOrNull()

        if (notificationsCountDto == null) {
            postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
            return
        }

        analyticsHelper.d { "Init Set NotificationsCountDto: $notificationsCountDto" }

        reduce {
            copy(
                notificationsCount = EnumMap<NotificationType, Int>(NotificationType::class.java).apply {
                    notificationsCountDto.notificationCountItems
                        .forEach { item -> put(item.type, item.count) }
                }.toMap(),
            )
        }
    }

    private suspend fun initSetNotifications() {
        val tempNotificationsTable = EnumMap<NotificationType, Map<Int, NotificationDto>>(NotificationType::class.java)

        NotificationType.entries.forEach { type ->
            val notificationsDto = getNotificationsUseCase(type = type).getOrNull()

            if (notificationsDto == null) {
                postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
                return
            }

            analyticsHelper.d { "Init Set NotificationsDto: $notificationsDto" }

            val tempNotificationMap: Map<Int, NotificationDto> = notificationsDto.notifications
                .mapIndexed { index, notification -> index to notification }
                .toMap()

            tempNotificationsTable.put(notificationsDto.type, tempNotificationMap)
        }

        reduce {
            copy(
                notifications = tempNotificationsTable.toMap(),
            )
        }
    }
}
