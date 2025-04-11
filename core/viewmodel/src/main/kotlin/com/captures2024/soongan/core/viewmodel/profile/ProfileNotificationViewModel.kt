package com.captures2024.soongan.core.viewmodel.profile

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
import com.captures2024.soongan.core.domain.usecase.notifications.DeleteNotificationUseCase
import com.captures2024.soongan.core.domain.usecase.notifications.GetNotificationsCountUseCase
import com.captures2024.soongan.core.domain.usecase.notifications.GetNotificationsUseCase
import com.captures2024.soongan.core.domain.usecase.notifications.PostNotificationReadUseCase
import com.captures2024.soongan.core.model.Notification
import com.captures2024.soongan.core.model.utils.NotificationSubType
import com.captures2024.soongan.core.model.utils.NotificationType
import com.captures2024.soongan.core.model.utils.NotificationsCountTable
import com.captures2024.soongan.core.model.utils.NotificationsTable
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.EnumMap
import javax.inject.Inject

@HiltViewModel
class ProfileNotificationViewModel
@Inject
constructor(
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val getNotificationsCountUseCase: GetNotificationsCountUseCase,
    private val postNotificationReadUseCase: PostNotificationReadUseCase,
    private val deleteNotificationUseCase: DeleteNotificationUseCase,
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
) : NewBaseViewModel<ProfileNotificationViewModel.State, ProfileNotificationViewModel.Effect, ProfileNotificationViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val isLoading: Boolean = false,
        val notifications: NotificationsTable = emptyMap(),
        val notificationsCount: NotificationsCountTable = emptyMap(),
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("notifications", notifications.toString()),
            LogElementArgument("notificationsCount", notificationsCount.toString()),
        )
    }

    sealed interface Effect : UISideEffect {

        data object NavigateToBack : Effect

        data class NavigateFromNotification(
            val subType: NotificationSubType,
            val url: String?, // redirectUrl
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object OnBackPressed : Intent

        data class OnClickNotification(
            val type: NotificationType,
            val indexKey: Int,
        ) : Intent

        data class OnDeleteNotification(
            val type: NotificationType,
            val indexKey: Int,
        ) : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State()

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
            message = "handleClientException",
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            Intent.Init -> loadingLaunch { handleInit() }
            Intent.OnBackPressed -> handleOnBackPressed()
            is Intent.OnClickNotification -> loadingLaunch { handleOnClickNotification(intent) }
            is Intent.OnDeleteNotification -> loadingLaunch { handleOnDeleteNotification(intent) }
        }
    }

    private suspend fun handleInit() {
//        임시 데이터 - 테스트 시 활성화
//        reduce {
//            copy(
//                notifications = mockNotificationsTable,
//                notificationsCount = mockNotificationsCountTable,
//            )
//        }
//        return

        initSetNotificationsCount()
        initSetNotifications()
    }

    private suspend fun initSetNotificationsCount() {
        val notificationsCountDto = getNotificationsCountUseCase().getOrNull()

        if (notificationsCountDto == null) {
            analyticsHelper.d(message = "Init Set NotificationsCountDto: null")

            return
        }

        analyticsHelper.d(message = "Init Set NotificationsCountDto: $notificationsCountDto")

        reduce {
            copy(
                notificationsCount = EnumMap<NotificationType, Int>(NotificationType::class.java).apply {
                    notificationsCountDto.notificationCountItems.forEach { item ->
                        put(item.type, item.count)
                    }
                }.toMap(),
            )
        }
    }

    private suspend fun initSetNotifications() {
        val tempNotificationsTable =
            EnumMap<NotificationType, Map<Int, Notification>>(NotificationType::class.java)

        NotificationType.entries.forEach { type ->
            val notificationsDto = getNotificationsUseCase(type = type).getOrNull()

            if (notificationsDto == null) {
                analyticsHelper.d(message = "Init Set NotificationsDto: null")

                return
            }

            analyticsHelper.d(message = "Init Set NotificationsDto: $notificationsDto")

            val tempNotificationMap: Map<Int, Notification> =
                notificationsDto.notifications.mapIndexed { index, notification ->
                    index to notification
                }.toMap()

            tempNotificationsTable.apply {
                put(notificationsDto.type, tempNotificationMap)
            }
        }

        reduce {
            copy(
                notifications = tempNotificationsTable.toMap(),
            )
        }
    }

    private fun handleOnBackPressed() {
        postSideEffect(Effect.NavigateToBack)
    }

    private suspend fun handleOnClickNotification(intent: Intent.OnClickNotification) {
        val targetNotificationMap = currentState.notifications[intent.type] ?: return
        val targetNotification = targetNotificationMap[intent.indexKey] ?: return

        val result = postNotificationReadUseCase(notificationId = targetNotification.id).getOrNull()
            ?: return

        if (result) {
            val updatedMap = targetNotificationMap.toMutableMap().apply {
                put(intent.indexKey, targetNotification.copy(isRead = true))
            }.toMap()

            reduce {
                copy(
                    notifications = notifications.toMutableMap().apply {
                        put(intent.type, updatedMap)
                    }.toMap(),
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
        val targetNotificationMap = currentState.notifications[intent.type] ?: return
        val targetNotification = targetNotificationMap[intent.indexKey] ?: return

        deleteNotificationUseCase(notificationId = targetNotification.id).getOrNull() ?: return

        val updatedMap = targetNotificationMap.toMutableMap().apply {
            remove(intent.indexKey)
        }.toMap()

        reduce {
            copy(
                notifications = currentState.notifications.toMutableMap().apply {
                    put(intent.type, updatedMap)
                }.toMap(),
            )
        }
    }
}
