package com.captures2024.soongan.feature.home

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.feature.home.state.home.HomeIntent
import com.captures2024.soongan.feature.home.state.home.HomeSideEffect
import com.captures2024.soongan.feature.home.state.home.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HomeUIState, HomeSideEffect, HomeIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): HomeUIState {
        return HomeUIState()
    }

    override fun handleClientException(throwable: Throwable) {
        TODO("Not yet implemented")
    }

    override suspend fun handleIntent(intent: HomeIntent) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "HomeVM"
    }
}