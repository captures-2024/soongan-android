package com.captures2024.soongan.presentation.viewmodel.main.post

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.core.navigator.screen.main.post.EditPostNavigator
import com.captures2024.soongan.domain.usecase.contest.EditPostTitleUseCase
import com.captures2024.soongan.domain.usecase.member.GetIsCurrentGuestModeUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.PostSingleButtonDialogUseCase
import com.captures2024.soongan.domain.usecase.system.dialog.SetIsShowGuestModeDialogFlowUseCase
import com.captures2024.soongan.domain.usecase.system.inapp.LaunchTermsUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ClearLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.HideLoadingUseCase
import com.captures2024.soongan.domain.usecase.system.loading.ShowLoadingUseCase
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
    private val editPostTitleUseCase: EditPostTitleUseCase,
    private val launchTermsUseCase: LaunchTermsUseCase,
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
        val postId: Long,
        val defaultUrl: String,
        val defaultTitle: String,
        val editTitle: String,
        val maxInputLength: Int,
        val roundId: Int,
        val subject: String,
        val isShowBackDialog: Boolean,
        val isOpenSubmitBottomSheet: Boolean,
        val isCheckedSubmitBottomSheet: Boolean,
    ) : UIState {
        val isEditable: Boolean
            get() = defaultTitle != editTitle && editTitle.isNotEmpty()
    }

    sealed interface Effect : UISideEffect {
        data object NavigateToBack : Effect
    }

    sealed interface Intent : UIIntent {

        data object OnClickBack : Intent

        data class OnTitleValueChanged(
            val newValue: String,
        ) : Intent

        data object OnClickEdit : Intent

        data object OnClickCancelBackDialog : Intent

        data object OnClickConfirmBackDialog : Intent

        data object OnClickCancelSubmitBottomSheet : Intent

        data object OnClickConfirmSubmitBottomSheet : Intent

        data object OnClickTermsSubmitBottomSheet : Intent

        data object OnClickCheckBoxSubmitBottomSheet : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        val route = savedStateHandle.toRoute<EditPostNavigator>()

        return State(
            postId = route.postId,
            defaultUrl = route.imageUrl,
            defaultTitle = route.title,
            editTitle = route.title,
            maxInputLength = AppConst.Main.Home.MAX_INPUT_LENGTH,
            roundId = route.roundId,
            subject = route.subject,
            isShowBackDialog = false,
            isOpenSubmitBottomSheet = false,
            isCheckedSubmitBottomSheet = false,
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnTitleValueChanged -> handleOnTitleValueChanged(intent)
            is Intent.OnClickEdit -> handleOnClickEdit()
            is Intent.OnClickCancelBackDialog -> handleOnClickCancelBackDialog()
            is Intent.OnClickConfirmBackDialog -> handleOnClickConfirmBackDialog()
            is Intent.OnClickCancelSubmitBottomSheet -> handleOnClickCancelSubmitBottomSheet()
            is Intent.OnClickConfirmSubmitBottomSheet -> loadingLaunch { handleOnClickConfirmSubmitBottomSheet() }
            is Intent.OnClickCheckBoxSubmitBottomSheet -> handleOnClickCheckBoxSubmitBottomSheet()
            is Intent.OnClickTermsSubmitBottomSheet -> loadingLaunch { handleOnClickTermsSubmitBottomSheet() }
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private fun handleOnClickBack() {
        showBackDialog()
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

    private fun handleOnClickEdit() {
        val submitData = currentState

        if (!submitData.isEditable) {
            analyticsHelper.d { "handleOnClickSubmit - isEditable = false" }
            return
        }

        showSubmitBottomSheet()
    }

    private fun handleOnClickCancelBackDialog() {
        dismissBackDialog()
    }

    private fun handleOnClickConfirmBackDialog() {
        dismissBackDialog()
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnClickCancelSubmitBottomSheet() {
        dismissSubmitBottomSheet()
    }

    private suspend fun handleOnClickConfirmSubmitBottomSheet() {
        dismissSubmitBottomSheet()

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

    private fun handleOnClickCheckBoxSubmitBottomSheet() {
        reduce {
            copy(
                isCheckedSubmitBottomSheet = !isCheckedSubmitBottomSheet,
            )
        }
    }

    private suspend fun handleOnClickTermsSubmitBottomSheet() {
        launchTermsUseCase()
    }

    private fun showBackDialog() {
        reduce {
            copy(
                isShowBackDialog = true,
            )
        }
    }

    private fun dismissBackDialog() {
        reduce {
            copy(
                isShowBackDialog = false,
            )
        }
    }

    private fun showSubmitBottomSheet() {
        reduce {
            copy(
                isOpenSubmitBottomSheet = true,
            )
        }
    }

    private fun dismissSubmitBottomSheet() {
        reduce {
            copy(
                isOpenSubmitBottomSheet = false,
            )
        }
    }
}
