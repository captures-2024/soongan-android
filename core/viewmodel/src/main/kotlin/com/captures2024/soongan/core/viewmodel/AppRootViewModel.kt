package com.captures2024.soongan.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.fcm.InitFcmUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetMemberInfoUseCase
import com.captures2024.soongan.core.domain.usecase.token.GetAllTokenUseCase
import com.captures2024.soongan.core.viewmodel.effect.AppRootSideEffect
import com.captures2024.soongan.core.viewmodel.intent.AppRootIntent
import com.captures2024.soongan.core.viewmodel.state.AppRootUIState
import com.captures2024.soongan.core.viewmodel.utils.AppRootRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AppRootViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val initFcmUseCase: InitFcmUseCase,
    private val getAllTokenUseCase: GetAllTokenUseCase,
    private val getMemberInfoUseCase: GetMemberInfoUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<AppRootUIState, AppRootSideEffect, AppRootIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): AppRootUIState = AppRootUIState()

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable)
    }

    override suspend fun handleIntent(intent: AppRootIntent) {
        when (intent) {
            is AppRootIntent.FetchFCMToken -> handleFetchFCMToken()

            is AppRootIntent.SuccessSign -> handleSuccessSign()

            is AppRootIntent.NavigateToMain -> handleNavigateToMain()

            is AppRootIntent.PatchMemberInfo -> handlePatchMemberInfo(intent)
        }
    }

    private suspend fun handleFetchFCMToken() {
        fetchRemoteFcmToken()
    }

    private suspend fun handleSuccessSign() {
        syncAllData()
    }

    private fun handleNavigateToMain() {
        reduce {
            copy(
                rootRouteState = AppRootRoute.MAIN,
            )
        }
    }

    private fun handlePatchMemberInfo(intent: AppRootIntent.PatchMemberInfo) {
        reduce {
            copy(
                memberInfo = currentState.patchMemberInfo(
                    nickname = intent.nickname,
                    birthYear = intent.birthYear,
                )
            )
        }

        handleNavigateToMain()
    }

    private suspend fun syncAllData() = launch(Dispatchers.IO) {
        val tokenResult = getAllTokenUseCase().getOrNull()

        if (tokenResult == null || tokenResult.first.isEmpty() || tokenResult.second.isEmpty()) {
            // 저장된 토큰 불러오기 실패, 토큰이 빈 경우
            postSideEffect(AppRootSideEffect.FailedRemoteSyncData)

            return@launch
        }

        val memberInfo = getMemberInfoUseCase().getOrNull()

        if (memberInfo == null) {
            // 토큰으로 조회되는 멤버가 없는 경우
            postSideEffect(AppRootSideEffect.FailedRemoteSyncData)
            return@launch
        }

        reduce {
            copy(
                memberInfo = memberInfo,
            )
        }

        val isNeedRegisterNickname = memberInfo.nickname == null
        val isNeedRegisterBirth = memberInfo.birthYear == null

        postSideEffect(
            AppRootSideEffect.SuccessRemoteSyncData(
                nickname = memberInfo.nickname,
                birthYear = memberInfo.birthYear,
            )
        )

        if (!isNeedRegisterNickname && !isNeedRegisterBirth) {
            fetchRootRoute(routeState = AppRootRoute.MAIN)
        } else {
            fetchRootRoute(routeState = AppRootRoute.SIGN)
        }
    }

    private fun fetchRootRoute(routeState: AppRootRoute) {
        reduce {
            copy(
                rootRouteState = routeState,
            )
        }

        analyticsHelper.d(
            LogElementArgument("routeState", "routeState = $routeState"),
            message = "fin fetchRootRoute",
        )
    }

    private suspend fun fetchRemoteFcmToken() = launch(Dispatchers.IO) {
        val result = initFcmUseCase().getOrNull()

        if (result != true) {
            analyticsHelper.d(
                LogElementArgument("result about init fcm", "result = $result"),
                message = "Fail to fetch RemoteFcmToken",
            )
        } else {
            analyticsHelper.d(message = "fin fetchRemoteFcmToken")
        }

        fetchRootRoute(AppRootRoute.SIGN)
    }
}