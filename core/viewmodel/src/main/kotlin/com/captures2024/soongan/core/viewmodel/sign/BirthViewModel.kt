package com.captures2024.soongan.core.viewmodel.sign

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.Validation
import com.captures2024.soongan.core.common.Validation.BirthYearValidState
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.PatchBirthYearUseCase
import com.captures2024.soongan.core.navigator.screen.sign.BirthNavigator
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BirthViewModel
@Inject
constructor(
    private val patchBirthYearUseCase: PatchBirthYearUseCase,
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    savedStateHandle: SavedStateHandle,
) : NewBaseViewModel<BirthViewModel.State, BirthViewModel.Effect, BirthViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    savedStateHandle = savedStateHandle,
) {
    data class State(
        val nickname: String,
        val birthYear: String = "",
        val maxBirthLength: Int = 4,
    ) : UIState {
        val isValid: BirthYearValidState
            get() = Validation.isValidBirthYear(birthYear)

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("nickname", nickname),
            LogElementArgument("birthYear", birthYear),
            LogElementArgument("maxBirthLength", maxBirthLength.toString()),
            LogElementArgument("isValid", isValid.toString()),
        )
    }

    sealed interface Effect : UISideEffect {
        data object NavigateToBack : Effect
    }

    sealed interface Intent : UIIntent {
        data object OnClickBack : Intent

        data class OnBirthValueChanged(
            val newValue: String,
        ) : Intent

        data object OnClickConfirm : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        val nickname = savedStateHandle.toRoute<BirthNavigator>().nickname

        return State(nickname = nickname)
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnBirthValueChanged -> handleOnBirthValueChanged(intent)
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnClickConfirm -> loadingLaunch { handleOnClickConfirm() }
        }
    }

    private fun handleOnBirthValueChanged(intent: Intent.OnBirthValueChanged) {
        if (intent.newValue.toIntOrNull() == null) {
            return
        }

        if (intent.newValue.length !in 0 .. currentState.maxBirthLength) {
            return
        }

        reduce {
            copy(
                birthYear = intent.newValue,
            )
        }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private suspend fun handleOnClickConfirm() {
        if (currentState.isValid != BirthYearValidState.Success) {
            return
        }

        val birthYear = currentState.birthYear.toIntOrNull() ?: return

        patchBirthYearUseCase(birthYear = birthYear).getOrNull()
    }
}
