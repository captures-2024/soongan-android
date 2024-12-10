package com.captures2024.soongan.feature.welcome

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.navigator.screen.main.welcome.WelcomeNavigator
import com.captures2024.soongan.feature.welcome.state.WelcomeIntent
import com.captures2024.soongan.feature.welcome.state.WelcomeSideEffect
import com.captures2024.soongan.feature.welcome.state.WelcomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
internal class WelcomeViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<WelcomeUIState, WelcomeSideEffect, WelcomeIntent>(savedStateHandle) {

    init {
        moveHome()
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): WelcomeUIState {
        val route = savedStateHandle.toRoute<WelcomeNavigator>()

        return WelcomeUIState(nickname = route.nickname)
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable)
    }

    override suspend fun handleIntent(intent: WelcomeIntent) {
        TODO("Not yet implemented")
    }

    private fun moveHome() {
        launch {
            delay(1000)
            postSideEffect(WelcomeSideEffect.NavigateToHome)
        }
    }
}