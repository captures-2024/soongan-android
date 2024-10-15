package com.captures2024.soongan.feature.signUp

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.navigator.screen.sign.BirthDateNavigator
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthDateIntent
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthDateSideEffect
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthDateUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class BirthYearViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<BirthDateUIState, BirthDateSideEffect, BirthDateIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): BirthDateUIState {
        val nickname = savedStateHandle.toRoute<BirthDateNavigator>().nickname
        return BirthDateUIState(nickname = nickname)
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
        )
    }

    override suspend fun handleIntent(intent: BirthDateIntent) {
        TODO("Not Impl Yet")
    }
}