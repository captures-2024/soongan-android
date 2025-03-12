package com.captures2024.soongan.core.viewmodel.post

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
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
import com.captures2024.soongan.core.domain.usecase.weekly.contests.EditPostTitleUseCase
import com.captures2024.soongan.core.navigator.screen.main.home.EditPostNavigator
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditPostViewModel
@Inject constructor(
    private val editPostTitleUseCase: EditPostTitleUseCase,
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
) : NewBaseViewModel<EditPostViewModel.State, EditPostViewModel.Effect, EditPostViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {
    data class State(
        val postId: Long = -1L,
        val imageUrl: String = "",
        val previousTitle: String = "",
        val title: String = "",
        val isEditable: Boolean = false,
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("postId", postId.toString()),
            LogElementArgument("imageUrl", imageUrl),
            LogElementArgument("previousTitle", previousTitle),
            LogElementArgument("title", title),
            LogElementArgument("isEditable", isEditable.toString()),
        )
    }

    sealed interface Effect : UISideEffect {

        data object NavigateToBack : Effect
    }

    sealed interface Intent : UIIntent {

        data object OnClickBack : Intent

        data class OnTitleValueChanged(
            val newValue: String,
        ) : Intent

        data object OnClickEditRemote : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        val data = savedStateHandle.toRoute<EditPostNavigator>()

        return State(
            postId = data.postId,
            imageUrl = data.imageUrl,
            previousTitle = data.title,
            title = data.title
        )
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable)
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickBack -> navigateBack()

            is Intent.OnClickEditRemote -> loadingLaunch { handleOnClickEditRemote() }

            is Intent.OnTitleValueChanged -> handleOnTitleValueChanged(intent)
        }
    }

    private fun navigateBack() = postSideEffect(Effect.NavigateToBack)

    private suspend fun handleOnClickEditRemote() {
        val result = editPostTitleUseCase(
            postId = currentState.postId,
            title = currentState.title
        ).getOrNull()

        analyticsHelper.d(message = "handleOnClickEditRemote - result: $result")

        if (result == null) {
            return
        }

        navigateBack()
    }

    private fun handleOnTitleValueChanged(intent: Intent.OnTitleValueChanged) {
        val newValue = intent.newValue

        if (newValue.length !in 0..15) {
            return
        }

        reduce {
            copy(
                title = newValue,
                isEditable = (previousTitle != newValue)
            )
        }
    }
}