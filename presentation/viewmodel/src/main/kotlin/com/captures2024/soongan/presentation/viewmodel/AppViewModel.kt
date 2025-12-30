package com.captures2024.soongan.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.domain.usecase.fcm.InitFcmUseCase
import com.captures2024.soongan.domain.usecase.member.GetCurrentMemberFlowUseCase
import com.captures2024.soongan.domain.usecase.member.GetGuestModeFlowUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.member.GetMemberInfoUseCase
import com.captures2024.soongan.domain.usecase.member.SetGuestModeUseCase
import com.captures2024.soongan.domain.usecase.notification.EmitNotificationUseCase
import com.captures2024.soongan.domain.usecase.system.appversion.CheckAppUpdateAvailableUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.GetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.GetSingleButtonDialogEventUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.inapp.GetInAppBrowserUrlFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.GetLoadingFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.domain.usecase.token.ClearAllTokenUseCase
import com.captures2024.soongan.presentation.viewmodel.model.AppRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel
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
    private val getSingleButtonDialogEventUseCase: GetSingleButtonDialogEventUseCase,
    private val initFcmUseCase: InitFcmUseCase,
    private val getMemberInfoUseCase: GetMemberInfoUseCase,
    private val getGuestModeFlowUseCase: GetGuestModeFlowUseCase,
    private val setGuestModeUseCase: SetGuestModeUseCase,
    private val clearAllTokenUseCase: ClearAllTokenUseCase,
    private val getLoadingFlowUseCase: GetLoadingFlowUseCase,
    private val getIsShowGuestModeDialogFlowUseCase: GetIsShowGuestModeDialogFlowUseCase,
    private val emitNotificationUseCase: EmitNotificationUseCase,
    private val getInAppBrowserUrlFlowUseCase: GetInAppBrowserUrlFlowUseCase,
    private val checkAppUpdateAvailableUseCase: CheckAppUpdateAvailableUseCase,
) : BaseViewModel<AppViewModel.State, AppViewModel.Effect, AppViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val isInitialized: Boolean,
        val isGuestMode: Boolean,
        val isLoading: Pair<Boolean, Long>,
        val isShowGuestModeDialog: Boolean,
        val currentMember: UserInfoDto?,
    ) : UIState {
        val rootRouteState: AppRoute
            get() = when (isInitialized) {
                // 앱 진입 성공
                true -> when (isGuestMode) {
                    // 게스트 모드 진입
                    true -> AppRoute.MAIN

                    // 게스트 모드 미진입
                    false -> when (currentMember) {
                        // 유저 데이터 미존재
                        null -> AppRoute.SIGN

                        // 유저 데이터 존재
                        else -> when {
                            // 유저 데이터 닉네임 && 생년 존재
                            currentMember.nickname != null && currentMember.birthYear != null -> AppRoute.MAIN

                            // 유저 데이터 닉네임 && 생년 미존재
                            else -> AppRoute.SIGN
                        }
                    }
                }

                // 앱 진입 실패
                false -> AppRoute.LANDING
            }

        override fun toString(): String {
            return "State(isInitialized=$isInitialized, isGuestMode=$isGuestMode, isLoading=$isLoading, isShowGuestModeDialog=$isShowGuestModeDialog, rootRouteState=$rootRouteState)"
        }
    }

    sealed interface Effect : UISideEffect {

        data object ShowVersionUpdateDialog : Effect

        data class ShowSingleButtonDialog(
            val type: CommonDialogType,
        ) : Effect

        data class OpenInAppBrowser(
            val url: String,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        //        TODO(InAppVersion 필요 시)
        data class CheckInAppUpdateAvailable(
            val isUpdateAvailable: Boolean,
        ) : Intent

        data object OnClickConfirmGuestModeDialog : Intent

        data object OnClickDismissGuestModeDialog : Intent

        data class PostNotification(
            val payload: Map<String, Any?>,
        ) : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State(
        isInitialized = false,
        isGuestMode = false,
        isLoading = false to System.currentTimeMillis(),
        isShowGuestModeDialog = false,
        currentMember = null,
    )

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable) { "state: $currentState" }
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> launch { handleInit() }
            is Intent.CheckInAppUpdateAvailable -> handleOnCheckInAppUpdateAvailable(intent)
            is Intent.OnClickConfirmGuestModeDialog -> loadingLaunch { handleOnClickConfirmGuestModeDialog() }
            is Intent.OnClickDismissGuestModeDialog -> handleOnClickDismissGuestModeDialog()
            is Intent.PostNotification -> handlePostNotification(intent)
        }
    }

    private suspend fun handleInit() {
        launch { collectCurrentMember() }
        launch { collectGuestMode() }
        launch { collectLoading() }
        launch { collectGuestModeDialog() }
        launch { collectInAppBrowserUrl() }
        launch { collectSingleButtonDialogEvent() }

        fetchRemoteFCMToken()
        fetchRemoteMemberInfo()

//        TODO(RemoteVersion 필요 시)
//        fetchRemoteUpdateAvailable()
    }

    private fun handleOnCheckInAppUpdateAvailable(intent: Intent.CheckInAppUpdateAvailable) {
        analyticsHelper.d { "handleOnCheckInAppUpdateAvailable - isUpdateAvailable : ${intent.isUpdateAvailable}" }

        reduce {
            copy(
                isInitialized = !intent.isUpdateAvailable,
            )
        }

        if (intent.isUpdateAvailable) {
            postSideEffect(Effect.ShowVersionUpdateDialog)
        }
    }

    private fun handleOnClickConfirmGuestModeDialog() {
        dismissGuestModeDialog()
        setGuestModeUseCase(false)
    }

    private fun handleOnClickDismissGuestModeDialog() {
        dismissGuestModeDialog()
    }

    private fun handlePostNotification(intent: Intent.PostNotification) {
        emitNotificationUseCase(intent.payload)
    }

    private suspend fun collectCurrentMember() {
        getCurrentMemberFlowUseCase().collect { info ->
            reduce {
                copy(
                    currentMember = info,
                )
            }
        }
    }

    private suspend fun collectGuestMode() {
        getGuestModeFlowUseCase().collect { isGuestMode ->
            reduce {
                copy(
                    isGuestMode = isGuestMode,
                )
            }
        }
    }

    private suspend fun collectLoading() {
        getLoadingFlowUseCase().collect {
            reduce {
                copy(
                    isLoading = it to System.currentTimeMillis(),
                )
            }
        }
    }

    private suspend fun collectGuestModeDialog() {
        getIsShowGuestModeDialogFlowUseCase().collect { condition ->
            reduce {
                copy(
                    isShowGuestModeDialog = condition,
                )
            }
        }
    }

    private suspend fun collectInAppBrowserUrl() {
        getInAppBrowserUrlFlowUseCase().collect {
            postSideEffect(Effect.OpenInAppBrowser(it))
        }
    }

    private suspend fun collectSingleButtonDialogEvent() {
        getSingleButtonDialogEventUseCase().collect {
            postSideEffect(Effect.ShowSingleButtonDialog(it))
        }
    }

    @Suppress("UnusedPrivateMember")
    private suspend fun fetchRemoteUpdateAvailable() {
        val isUpdateAvailable = checkAppUpdateAvailableUseCase().getOrNull()

        analyticsHelper.d { "fetchRemoteUpdateAvailable - isUpdateAvailable : $isUpdateAvailable" }

        reduce {
            copy(
                isInitialized = (isUpdateAvailable != true),
            )
        }

        if (isUpdateAvailable == true) {
            postSideEffect(Effect.ShowVersionUpdateDialog)
        }
    }

    private suspend fun fetchRemoteFCMToken() {
        val result = initFcmUseCase().getOrNull()

        val logMessage = when (result) {
            true -> "success fetch RemoteFcmToken"
            false -> "fail fetch RemoteFcmToken"
            else -> "already registered RemoteFcmToken"
        }

        analyticsHelper.d { "result about init fcm $logMessage, result = $result" }
    }

    private suspend fun fetchRemoteMemberInfo() {
        val result = getMemberInfoUseCase().getOrNull()

        analyticsHelper.d { "fetchRemoteMemberInfo - result: $result" }

        if (result == null) {
            clearAllTokenUseCase()
        }
    }
}
