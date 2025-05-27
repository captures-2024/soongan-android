package com.captures2024.soongan.presentation.viewmodel.main.post

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.navigator.screen.main.post.ImageViewerNavigator
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewerViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ImageViewerViewModel.State, ImageViewerViewModel.Effect, ImageViewerViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val url: String,
    ) : UIState

    sealed interface Effect : UISideEffect {
        data object NavigateToBack : Effect
    }

    sealed interface Intent : UIIntent {
        data object OnClickBack : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        val route = savedStateHandle.toRoute<ImageViewerNavigator>()

        return State(
            url = route.url,
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickBack -> handleOnClickBack()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }
}
