package com.captures2024.soongan

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.fcm.InitFcmUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetMemberInformationUseCase
import com.captures2024.soongan.core.domain.usecase.token.GetAllTokenUseCase
import com.captures2024.soongan.state.AppRootIntent
import com.captures2024.soongan.state.AppRootRouteState
import com.captures2024.soongan.state.AppRootSideEffect
import com.captures2024.soongan.state.AppRootUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
internal class AppRootViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val initFcmUseCase: InitFcmUseCase,
    private val getAllTokenUseCase: GetAllTokenUseCase,
    private val getMemberInformationUseCase: GetMemberInformationUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<AppRootUIState, AppRootSideEffect, AppRootIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): AppRootUIState = AppRootUIState()

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable)
    }

    override suspend fun handleIntent(intent: AppRootIntent) {
        when (intent) {
            is AppRootIntent.FetchFCMToken -> handleFetchFCMToken(intent)

            is AppRootIntent.SuccessSign -> handleSuccessSign()
        }
    }

    private suspend fun handleFetchFCMToken(intent: AppRootIntent.FetchFCMToken) {
        reduce {
            copy(
                fcmToken = intent.token,
            )
        }

        fetchRemoteFcmToken(fcmToken = intent.token)
    }

    private suspend fun handleSuccessSign() {
        syncAllData(isSignSession = true)
    }

    private suspend fun syncAllData(isSignSession: Boolean = false) = launch(Dispatchers.IO) {
        val tokenResult = getAllTokenUseCase().getOrNull()

        if (tokenResult == null) {
            // 저장된 토큰 불러오기 실패
            emitSignSession(isSignSession)
            return@launch
        }

        if (tokenResult.first.isEmpty() || tokenResult.second.isEmpty()) {
            // 저장된 토큰이 빈 경우
            emitSignSession(isSignSession)
            return@launch
        }

        val memberInfo = getMemberInformationUseCase().getOrNull()

        if (memberInfo == null) {
            // 토큰으로 조회되는 멤버가 없는 경우
            emitSignSession(isSignSession)
            return@launch
        }

        reduce {
            copy(
                accessToken = tokenResult.first,
                refreshToken = tokenResult.second,
                memberInfo = memberInfo,
            )
        }

        val isNeedRegisterNickname = memberInfo.user.nickname.isEmpty()
        val isNeedRegisterBirth = memberInfo.user.birthDate.isEmpty()

        postSideEffect(
            AppRootSideEffect.SuccessRemoteSyncData(
                isNeedRegisterNickname = isNeedRegisterNickname,
                isNeedRegisterBirth = isNeedRegisterBirth,
            )
        )

        if (!isNeedRegisterNickname && !isNeedRegisterBirth) {
            fetchRootRoute(routeState = AppRootRouteState.MAIN)
        } else {
            fetchRootRoute(routeState = AppRootRouteState.SIGN)
        }
    }

    private fun emitSignSession(isSignSession: Boolean) {
        when (isSignSession) {
            true -> {
                postSideEffect(AppRootSideEffect.FailedRemoteSyncData)
            }

            false -> fetchRootRoute(AppRootRouteState.SIGN)
        }
    }

    private fun fetchRootRoute(routeState: AppRootRouteState) {
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

    private suspend fun fetchRemoteFcmToken(fcmToken: String) = launch(Dispatchers.IO) {
        postSideEffect(AppRootSideEffect.FetchFcmToken(fcmToken))

        val result = initFcmUseCase(fcmToken = fcmToken)

        analyticsHelper.d(
            LogElementArgument("fcmToken", "fcmToken = $fcmToken"),
            LogElementArgument("result", "result = $result"),
            message = "fin fetchRemoteFcmToken",
        )

        syncAllData()
    }
}