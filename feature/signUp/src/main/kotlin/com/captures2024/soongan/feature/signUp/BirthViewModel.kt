package com.captures2024.soongan.feature.signUp

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.members.PatchBirthYearUseCase
import com.captures2024.soongan.core.navigator.screen.sign.BirthNavigator
import com.captures2024.soongan.feature.signUp.state.birth.BirthIntent
import com.captures2024.soongan.feature.signUp.state.birth.BirthSideEffect
import com.captures2024.soongan.feature.signUp.state.birth.BirthUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class BirthViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val patchBirthYearUseCase: PatchBirthYearUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<BirthUIState, BirthSideEffect, BirthIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): BirthUIState {
        val nickname = savedStateHandle.toRoute<BirthNavigator>().nickname
        return BirthUIState(nickname = nickname)
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
        )
    }

    override suspend fun handleIntent(intent: BirthIntent) {
        when (intent) {
            is BirthIntent.OnClickBack -> handleOnClickBack()

            is BirthIntent.OnValueChanged -> handleOnValueChanged(intent)

            is BirthIntent.OnClickConfirm -> handleOnClickConfirm()
        }
    }

    private fun handleOnClickBack() {
        postSideEffect(BirthSideEffect.NavigateToBack)
    }

    private fun handleOnValueChanged(intent: BirthIntent.OnValueChanged) {
        if (intent.birthYear.toIntOrNull() == null) {
            return
        }

        reduce {
            copy(
                birthYear = intent.birthYear
            )
        }
    }

    private fun handleOnClickConfirm() {
        reduce {
            copy(
                isLoading = true,
            )
        }

        if (currentState.isValid != Validation.BirthYearValidState.Success) {
            reduce {
                copy(
                    isLoading = false,
                )
            }

            return
        }

        val birthYear = currentState.birthYear.toInt()

        launch {
            patchBirthYearUseCase(birthYear = birthYear).getOrNull()

            reduce {
                copy(
                    isLoading = false,
                )
            }
        }
    }
}