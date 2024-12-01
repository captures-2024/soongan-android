package com.captures2024.soongan

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
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
    private val getAllTokenUseCase: GetAllTokenUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<AppRootUIState, AppRootSideEffect, AppRootIntent>(savedStateHandle) {

    init {
        refreshTokenData()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): AppRootUIState = AppRootUIState()

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable)
    }

    override suspend fun handleIntent(intent: AppRootIntent) {

    }

    private fun refreshTokenData() = launch(Dispatchers.IO) {
        analyticsHelper.d(message = "entry refreshTokenData")

        val tokenResult = getAllTokenUseCase().getOrNull()

        if (tokenResult == null) {
            fetchRootRoute(AppRootRouteState.SIGN)
            return@launch
        }

        if (tokenResult.first.isEmpty() || tokenResult.second.isEmpty()) {
            fetchRootRoute(AppRootRouteState.SIGN)
            return@launch
        }

        reduce {
            copy(
                accessToken = tokenResult.first,
                refreshToken = tokenResult.second,
            )
        }

        analyticsHelper.d(
            LogElementArgument("tokenResult", "tokenResult = $tokenResult"),
            message = "fin refreshTokenData",
        )
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
}