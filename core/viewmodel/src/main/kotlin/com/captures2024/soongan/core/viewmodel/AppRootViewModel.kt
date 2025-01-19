package com.captures2024.soongan.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.fcm.InitFcmUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.GetLoadingFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetCurrentMemberFlow
import com.captures2024.soongan.core.domain.usecase.members.GetGuestModeFlowUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetMemberInfoUseCase
import com.captures2024.soongan.core.domain.usecase.token.ClearAllTokenUseCase
import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.viewmodel.model.AppRootRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppRootViewModel
@Inject
constructor(
    private val getCurrentMemberFlow: GetCurrentMemberFlow,
    private val initFcmUseCase: InitFcmUseCase,
    private val getMemberInfoUseCase: GetMemberInfoUseCase,
    private val getGuestModeFlowUseCase: GetGuestModeFlowUseCase,
    private val clearAllTokenUseCase: ClearAllTokenUseCase,
    private val getLoadingFlowUseCase: GetLoadingFlowUseCase,
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    savedStateHandle: SavedStateHandle,
) : NewBaseViewModel<AppRootViewModel.State, AppRootViewModel.Effect, AppRootViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val isInitialized: Boolean = false,
        val isGuestMode: Boolean = false,
        val currentMember: UserInfoDto? = null,
        val isLoading: Pair<Boolean, Long> = false to System.currentTimeMillis(),
    ) : UIState {

        val rootRouteState: AppRootRoute
            get() = when (isInitialized) {
                // 앱 진입 성공
                true -> when (isGuestMode) {
                    // 게스트 모드 진입
                    true -> AppRootRoute.MAIN

                    // 게스트 모드 미진입
                    false -> when (currentMember) {
                        // 유저 데이터 미존재
                        null -> AppRootRoute.SIGN

                        // 유저 데이터 존재
                        else -> when {
                            // 유저 데이터 닉네임 && 생년 미존재
                            currentMember.nickname != null && currentMember.birthYear != null -> AppRootRoute.MAIN

                            // 유저 데이터 닉네임 && 생년 존재
                            else -> AppRootRoute.SIGN
                        }
                    }
                }

                // 앱 진입 실패
                false -> AppRootRoute.LANDING
            }

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isInitialized", isInitialized.toString()),
            LogElementArgument("currentMember", currentMember.toString()),
        )
    }

    sealed interface Effect : UISideEffect

    sealed interface Intent : UIIntent {

        data object Init : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State()
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable)
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> launch { handleInit() }
        }
    }

    private suspend fun handleInit() {
        launch { collectCurrentMember() }
        launch { collectGuestMode() }
        launch { collectLoading() }

        fetchRemoteFCMToken()
        fetchRemoteMemberInfo()

        reduce {
            copy(
                isInitialized = true,
            )
        }
    }

    private suspend fun collectCurrentMember() {
        getCurrentMemberFlow().collect { info ->
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
            reduce { copy(isLoading = it to System.currentTimeMillis()) }
        }
    }

    private suspend fun fetchRemoteFCMToken() {
        val result = initFcmUseCase().getOrNull()

        val logMessage = when (result) {
            true -> "success fetch RemoteFcmToken"
            false -> "fail fetch RemoteFcmToken"
            else -> "already registered RemoteFcmToken"
        }

        analyticsHelper.d(
            LogElementArgument("result about init fcm", "result = $result"),
            message = logMessage,
        )
    }

    private suspend fun fetchRemoteMemberInfo() {
        val result = getMemberInfoUseCase().getOrNull()

        if (result == null) {
            clearAllTokenUseCase()
        }
    }
}
