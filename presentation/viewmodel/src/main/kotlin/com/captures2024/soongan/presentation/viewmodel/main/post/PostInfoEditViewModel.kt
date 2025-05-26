package com.captures2024.soongan.presentation.viewmodel.main.post

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.dialog.PostSingleButtonDialogUseCase
import com.captures2024.soongan.core.domain.usecase.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.members.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.EditPostTitleUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetWeeklyContestInfoListUseCase
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.core.navigator.screen.main.post.EditPostNavigator
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostInfoEditViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val postSingleButtonDialogUseCase: PostSingleButtonDialogUseCase,
    private val getWeeklyContestInfoListUseCase: GetWeeklyContestInfoListUseCase,
    private val editPostTitleUseCase: EditPostTitleUseCase,
) : BaseViewModel<PostInfoEditViewModel.State, PostInfoEditViewModel.Effect, PostInfoEditViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val round: Int,
        val subject: String,
        val postId: Long,
        val defaultUrl: String,
        val defaultTitle: String,
        val editTitle: String,
        val maxInputLength: Int,
        val isShowInitErrorDialog: Boolean,
    ) : UIState {
        val isEditable: Boolean
            get() = defaultTitle != editTitle && editTitle.isNotEmpty()
    }

    sealed interface Effect : UISideEffect {
        data object NavigateToBack : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data object OnClickBack : Intent

        data class OnTitleValueChanged(
            val newValue: String,
        ) : Intent

        data object OnClickConfirmInitErrorDialog : Intent

        data object OnClickEdit : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        val route = savedStateHandle.toRoute<EditPostNavigator>()

        return State(
            round = 0,
            subject = AppConst.EMPTY_STRING,
            postId = route.postId,
            defaultUrl = route.imageUrl,
            defaultTitle = route.title,
            editTitle = route.title,
            maxInputLength = AppConst.Main.Home.MAX_INPUT_LENGTH,
            isShowInitErrorDialog = false,
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnTitleValueChanged -> handleOnTitleValueChanged(intent)
            is Intent.OnClickConfirmInitErrorDialog -> handleOnClickConfirmInitErrorDialog()
            is Intent.OnClickEdit -> loadingLaunch { handleOnClickEdit() }
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private suspend fun handleInit() {
        val weeklyInfo = getWeeklyContestInfoListUseCase
            .invoke()
            .getOrNull()
            ?.weeklyContestInfoList
            ?.lastOrNull()

        if (weeklyInfo == null) {
            showInitErrorDialog()
            return
        }

        reduce {
            copy(
                round = weeklyInfo.round,
                subject = weeklyInfo.subject,
            )
        }
    }

    private fun handleOnClickBack() {
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnTitleValueChanged(intent: Intent.OnTitleValueChanged) {
        val newValue = intent.newValue

        if (newValue.length > currentState.maxInputLength) {
            return
        }

        reduce {
            copy(
                editTitle = newValue,
            )
        }
    }

    private fun handleOnClickConfirmInitErrorDialog() {
        dismissInitErrorDialog()
        postSideEffect(Effect.NavigateToBack)
    }

    private suspend fun handleOnClickEdit() {
        val state = currentState

        val result = editPostTitleUseCase(
            postId = state.postId,
            title = state.editTitle,
        ).getOrNull()

        if (result == null) {
            postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
            return
        }

        postSideEffect(Effect.NavigateToBack)
    }

    private fun showInitErrorDialog() {
        reduce {
            copy(
                isShowInitErrorDialog = true,
            )
        }
    }

    private fun dismissInitErrorDialog() {
        reduce {
            copy(
                isShowInitErrorDialog = false,
            )
        }
    }
}
