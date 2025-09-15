package com.captures2024.soongan.presentation.viewmodel.main.profile

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.navigator.screen.main.profile.ExplainNavigator
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.report.PostExplainUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExplainViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val postExplainUseCase: PostExplainUseCase,
) : BaseViewModel<ExplainViewModel.State, ExplainViewModel.Effect, ExplainViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        internal val postId: Long,
        val postTitle: String = "",
        val postImageUrl: String = "",
        val explainContent: String = "",
        val maxExplainContentSize: Int = 1000,
    ) : UIState {
        val isEnabled: Boolean
            get() = explainContent.isNotEmpty()

        override fun toString(): String {
            return "State(postTitle='$postTitle', postImageUrl='${postImageUrl.length}', explainContent='$explainContent', maxExplainContentSize='$maxExplainContentSize', isEnabled=$isEnabled)"
        }
    }

    sealed interface Effect : UISideEffect {
        data object NavigateToBack : Effect

        data object NavigateToCompleteExplain : Effect
    }

    sealed interface Intent : UIIntent {
        data object Init : Intent

        data object OnClickBack : Intent

        data class OnExplainValueChange(
            val value: String,
        ) : Intent

        data object OnClickReport : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        val route = savedStateHandle.toRoute<ExplainNavigator>()

        return State(
            postId = route.postId,
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> handleInit()
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnClickReport -> loadingLaunch { handleOnClickReport() }
            is Intent.OnExplainValueChange -> handleOnExplainValueChange(intent)
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable) { "state: $currentState" }
    }

    private fun handleInit() {
        val currentPostId = currentState.postId

        if (currentPostId == -1L) {
            postSideEffect(Effect.NavigateToBack)
        }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private suspend fun handleOnClickReport() {
        val state = currentState

        val result = postExplainUseCase(
            targetId = state.postId,
            explain = state.explainContent,
        ).getOrNull()

        if (result == true) {
            postSideEffect(Effect.NavigateToCompleteExplain)
        }
    }

    private fun handleOnExplainValueChange(intent: Intent.OnExplainValueChange) {
        if (intent.value.length > currentState.maxExplainContentSize) {
            return
        }

        reduce {
            copy(
                explainContent = intent.value,
            )
        }
    }
}
