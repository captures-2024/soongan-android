package com.captures2024.soongan.core.viewmodel.sign

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class TermsOfUseViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
) : NewBaseViewModel<TermsOfUseViewModel.State, TermsOfUseViewModel.Effect, TermsOfUseViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val url: String = "https://abyssinian-cherry-9fc.notion.site/5724dc92a43c4e7e94fd5ccf8ab0608b",
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("url", url),
        )
    }

    sealed interface Effect : UISideEffect {
        data object NavigateToBack : Effect

        data object NavigateToTerms : Effect
    }

    sealed interface Intent : UIIntent {
        data object Init : Intent

        data object OnClickBack : Intent

        data object OnClickTerms : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State()

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable)
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> launch { handleInit() }
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnClickTerms -> handleOnClickTerms()
        }
    }

    private suspend fun handleInit() {
        delay(500)
        postSideEffect(Effect.NavigateToTerms)
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnClickTerms() {
        postSideEffect(Effect.NavigateToTerms)
    }
}
