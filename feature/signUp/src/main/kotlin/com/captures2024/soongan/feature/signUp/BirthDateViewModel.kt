package com.captures2024.soongan.feature.signUp

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.domain.usecase.members.PatchBirthYearUseCase
import com.captures2024.soongan.core.navigator.screen.sign.BirthDateNavigator
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthIntent
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthSideEffect
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class BirthDateViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val patchBirthYearUseCase: PatchBirthYearUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<BirthUIState, BirthSideEffect, BirthIntent>(savedStateHandle) {

    override fun createInitialState(savedStateHandle: SavedStateHandle): BirthUIState {
        val nickname = savedStateHandle.toRoute<BirthDateNavigator>().nickname
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
            val isPatched = patchBirthYearUseCase(birthYear = birthYear).getOrNull()

            if (isPatched == null) {
                reduce {
                    copy(
                        isLoading = false,
                    )
                }

                return@launch
            }

            when (isPatched) {
                true -> {
                    reduce {
                        copy(
                            isLoading = false,
                        )
                    }

                    postSideEffect(
                        sideEffect = BirthSideEffect.NavigateToMain(
                            nickname = currentState.nickname,
                            birthYear = birthYear,
                        )
                    )
                    return@launch
                }

                false -> {
                    reduce {
                        copy(
                            isLoading = false,
                        )
                    }

                    return@launch
                }
            }
        }
    }
}