package com.captures2024.soongan.presentation.viewmodel.main.profile

import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.inapp.LaunchInquiryUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import com.captures2024.soongan.presentation.viewmodel.model.FaqCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FaqViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val launchInquiryUseCase: LaunchInquiryUseCase,
) : BaseViewModel<FaqViewModel.State, FaqViewModel.Effect, FaqViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val categories: List<FaqCategory>,
        val selectedCategory: FaqCategory,
    ) : UIState

    sealed interface Effect : UISideEffect {
        data object NavigateToBack : Effect
    }

    sealed interface Intent : UIIntent {
        data object OnClickBack : Intent

        data class OnClickCategory(
            val category: FaqCategory,
        ) : Intent

        data object OnClickInquiry : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        return State(
            categories = FaqCategory.entries,
            selectedCategory = FaqCategory.DEFAULT,
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnClickCategory -> handleOnClickCategory(intent)
            is Intent.OnClickInquiry -> loadingLaunch { handleOnClickInquiry() }
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnClickCategory(intent: Intent.OnClickCategory) {
        reduce {
            copy(
                selectedCategory = intent.category,
            )
        }
    }

    private suspend fun handleOnClickInquiry() {
        launchInquiryUseCase()
    }
}
