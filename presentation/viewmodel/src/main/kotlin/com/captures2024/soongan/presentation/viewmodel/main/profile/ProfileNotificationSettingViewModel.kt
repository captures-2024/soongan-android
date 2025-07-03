package com.captures2024.soongan.presentation.viewmodel.main.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.dto.NotificationSettingDto
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.notification.GetNotificationSettingsUseCase
import com.captures2024.soongan.domain.usecase.notification.PatchNotificationSettingsUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.PostSingleButtonDialogUseCase
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
    private val postSingleButtonDialogUseCase: PostSingleButtonDialogUseCase,
    private val getNotificationSettingsUseCase: GetNotificationSettingsUseCase,
    private val patchNotificationSettingsUseCase: PatchNotificationSettingsUseCase,
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
        val isInit: Boolean,
        val isCheckedPermission: Boolean,
        val notificationSettingState: NotificationSettingState,
        val initNotificationSettingState: NotificationSettingState?,
        val isShowPermissionDialog: Boolean,
    ) : UIState {

        data class NotificationSettingState(
            val contest: Boolean = false,
            val activity: Boolean = false,
            val notice: Boolean = false,
        ) {
            val all: Boolean
                get() = contest && activity && notice
            fun getStateByType(type: NotificationSettingType): Boolean = when (type) {
                NotificationSettingType.ALL -> all
                NotificationSettingType.CONTEST -> contest
                NotificationSettingType.ACTIVITY -> activity
                NotificationSettingType.NOTICE -> notice
            }

            fun switch(type: NotificationSettingType): NotificationSettingState = when (type) {
                NotificationSettingType.ALL -> when (all) {
                    true -> copy(
                        contest = false,
                        activity = false,
                        notice = false,
                    )

                    false -> copy(
                        contest = true,
                        activity = true,
                        notice = true,
                    )
                }
                NotificationSettingType.CONTEST -> copy(contest = !contest)
                NotificationSettingType.ACTIVITY -> copy(activity = !activity)
                NotificationSettingType.NOTICE -> copy(notice = !notice)
            }
        }
    }

    sealed interface Effect : UISideEffect {
        data object NavigateToBack : Effect

        data object CheckPermission : Effect

        data object OpenAppSetting : Effect
    }

    sealed interface Intent : UIIntent {
        data object Init : Intent

        data object CheckPermission : Intent

        data object OnClickBack : Intent

        data class OnSwitchNotificationSettingType(
            val type: NotificationSettingType,
        ) : Intent

        data class Permission(
            val isGranted: Boolean,
        ) : Intent

        data object OnClickCancelPermissionDialog : Intent

        data object OnClickConfirmPermissionDialog : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State(
            isInit = false,
            isCheckedPermission = false,
            notificationSettingState = State.NotificationSettingState(),
            initNotificationSettingState = null,
            isShowPermissionDialog = false,
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }
            is Intent.CheckPermission -> handleCheckPermission()
            is Intent.OnClickBack -> loadingLaunch { handleOnClickBack() }
            is Intent.OnSwitchNotificationSettingType -> handleOnSwitchNotificationSettingType(intent)
            is Intent.Permission -> handlePermission(intent)
            is Intent.OnClickCancelPermissionDialog -> handleOnClickCancelPermissionDialog()
            is Intent.OnClickConfirmPermissionDialog -> handleOnClickConfirmPermissionDialog()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private suspend fun handleInit() {
        val notificationSettings = getNotificationSettingsUseCase().getOrNull()

        if (notificationSettings == null) {
            errorBack()
            return
        }

        reduce {
            copy(
                isInit = true,
                notificationSettingState = notificationSettingState.copy(
                    contest = notificationSettings.contestPush,
                    activity = notificationSettings.activityPush,
                    notice = notificationSettings.noticePush,
                ),
                initNotificationSettingState = notificationSettingState.copy(
                    contest = notificationSettings.contestPush,
                    activity = notificationSettings.activityPush,
                    notice = notificationSettings.noticePush,
                ),
            )
        }
    }

    private fun handleCheckPermission() {
        reduce {
            copy(
                isCheckedPermission = true,
            )
        }

        postSideEffect(Effect.CheckPermission)
    }

    private suspend fun handleOnClickBack() {
        defaultBack()
    }

    private fun handleOnSwitchNotificationSettingType(intent: Intent.OnSwitchNotificationSettingType) {
        val notificationSettingState = currentState.notificationSettingState

        reduce {
            copy(
                notificationSettingState = notificationSettingState.switch(intent.type),
            )
        }
    }

    private fun handlePermission(intent: Intent.Permission) {
        if (!intent.isGranted) {
            showPermissionDialog()
        }
    }

    private fun handleOnClickCancelPermissionDialog() {
        dismissPermissionDialog()
    }

    private fun handleOnClickConfirmPermissionDialog() {
        dismissPermissionDialog()

        postSideEffect(Effect.OpenAppSetting)
    }

    private suspend fun defaultBack() {
        val state = currentState

        if (state.notificationSettingState != state.initNotificationSettingState) {
            val result = patchNotificationSettingsUseCase(
                settings = NotificationSettingDto(
                    contestPush = state.notificationSettingState.contest,
                    activityPush = state.notificationSettingState.activity,
                    noticePush = state.notificationSettingState.notice,
                ),
            ).getOrNull()

            if (result == null) {
                postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
                return
            }
        }

        postSideEffect(Effect.NavigateToBack)
    }

    private fun errorBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun showPermissionDialog() {
        reduce {
            copy(
                isShowPermissionDialog = true,
            )
        }
    }

    private fun dismissPermissionDialog() {
        reduce {
            copy(
                isShowPermissionDialog = false,
            )
        }
    }
}
