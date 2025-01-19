package com.captures2024.soongan.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.fcm.InitFcmUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetMemberInfoUseCase
import com.captures2024.soongan.core.domain.usecase.token.GetAllTokenUseCase
import com.captures2024.soongan.core.model.dto.UserInfoDto
import com.captures2024.soongan.core.viewmodel.model.AppRootRoute
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
) : BaseViewModel<AppRootViewModel.State, AppRootViewModel.Effect, AppRootViewModel.Intent>(savedStateHandle) {

    data class State(
        val isLoading: Boolean = false,
        val rootRouteState: AppRootRoute = AppRootRoute.LANDING,
        private val memberInfo: UserInfoDto = UserInfoDto.defaultBuilder(),
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("rootRouteState", rootRouteState.toString()),
            LogElementArgument("memberInfo", memberInfo.toString()),
        )

        fun isGuestMode(): Boolean = memberInfo.email.isEmpty()

        fun getNickname(): String = memberInfo.nickname ?: ""

        fun patchMemberInfo(
            nickname: String,
            birthYear: Int,
        ): UserInfoDto = memberInfo.copy(
            nickname = nickname,
            birthYear = birthYear,
        )
    }

    sealed interface Effect : UISideEffect {

        data object FailedRemoteSyncData : Effect

        data class SuccessRemoteSyncData(
            val nickname: String?,
            val birthYear: Int?,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object FetchFCMToken : Intent

        data object SuccessSign : Intent

        data object NavigateToMain : Intent

        data class PatchMemberInfo(
            val nickname: String,
            val birthYear: Int,
        ) : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State()
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable)
    }

    override suspend fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.FetchFCMToken -> handleFetchFCMToken()

            is Intent.SuccessSign -> handleSuccessSign()

            is Intent.NavigateToMain -> handleNavigateToMain()

            is Intent.PatchMemberInfo -> handlePatchMemberInfo(intent)
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

    private fun handlePatchMemberInfo(intent: Intent.PatchMemberInfo) {
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
            postSideEffect(Effect.FailedRemoteSyncData)

            return@launch
        }

        val memberInfo = getMemberInfoUseCase().getOrNull()

        if (memberInfo == null) {
            // 토큰으로 조회되는 멤버가 없는 경우
            postSideEffect(Effect.FailedRemoteSyncData)
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
            Effect.SuccessRemoteSyncData(
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

        val logMessage = when (result) {
            true -> "success fetch RemoteFcmToken"
            false -> "fail fetch RemoteFcmToken"
            else -> "already registered RemoteFcmToken"
        }

        analyticsHelper.d(
            LogElementArgument("result about init fcm", "result = $result"),
            message = logMessage,
        )

        fetchRootRoute(AppRootRoute.SIGN)
    }
}
