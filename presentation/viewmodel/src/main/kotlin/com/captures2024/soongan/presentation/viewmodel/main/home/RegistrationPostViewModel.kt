package com.captures2024.soongan.presentation.viewmodel.main.home

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
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
import com.captures2024.soongan.core.domain.usecase.weekly.contests.GetWeeklyContestInfoListUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.RegisterPostUseCase
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.dto.WeeklyContestInfoDto
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationPostViewModel
@Inject
constructor(
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    getIsCurrentGuestModeUseCase: GetIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase: SetIsShowGuestModeDialogFlowUseCase,
    savedStateHandle: SavedStateHandle,
    private val getWeeklyContestInfoListUseCase: GetWeeklyContestInfoListUseCase,
    private val registerPostUseCase: RegisterPostUseCase,
    private val postSingleButtonDialogUseCase: PostSingleButtonDialogUseCase,
) : BaseViewModel<RegistrationPostViewModel.State, RegistrationPostViewModel.Effect, RegistrationPostViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {
    data class State(
        val currentMedia: Uri?,
        val currentContestInfo: WeeklyContestInfoDto?,
        val title: String,
        val maxInputLength: Int,
        val isShowBackDialog: Boolean,
        val isOpenSubmitBottomSheet: Boolean,
    ) : UIState {

        override fun toString(): String {
            return "State(currentMedia=${currentMedia?.toString()?.length}, currentContestInfo=$currentContestInfo, title='$title', maxInputLength=$maxInputLength, isShowBackDialog=$isShowBackDialog, isOpenSubmitBottomSheet=$isOpenSubmitBottomSheet)"
        }
    }

    sealed interface Effect : UISideEffect {
        data object OpenMediaPicker : Effect

        data object NavigateToBack : Effect

        data class NavigateToPost(
            val postId: Long,
        ) : Effect
    }

    sealed interface Intent : UIIntent {
        data object Init : Intent

        data object OpenMediaPicker : Intent

        data class InitMedia(
            val mediaUri: Uri?,
        ) : Intent

        data object OnClickBack : Intent

        data object OnClickCancelBackDialog : Intent

        data object OnClickConfirmBackDialog : Intent

        data class OnTitleValueChanged(
            val newValue: String,
        ) : Intent

        data object OnClickSubmit : Intent

        data object OnClickCancelSubmitBottomSheet : Intent

        data object OnClickConfirmSubmitBottomSheet : Intent
    }

    init {
        intent(Intent.Init)
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State(
        currentMedia = null,
        currentContestInfo = null,
        title = AppConst.EMPTY_STRING,
        maxInputLength = AppConst.Main.Home.MAX_INPUT_LENGTH,
        isShowBackDialog = false,
        isOpenSubmitBottomSheet = false,
    )

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable = throwable) { "state: $currentState" }
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> loadingLaunch { handleInit() }
            is Intent.OpenMediaPicker -> handleOpenMediaPicker()
            is Intent.InitMedia -> handleInitMedia(intent)
            is Intent.OnClickBack -> handleOnClickBack()
            is Intent.OnClickCancelBackDialog -> handleOnClickCancelBackDialog()
            is Intent.OnClickConfirmBackDialog -> handleOnClickConfirmBackDialog()
            is Intent.OnTitleValueChanged -> handleOnTitleValueChanged(intent)
            is Intent.OnClickSubmit -> handleOnClickSubmit()
            is Intent.OnClickCancelSubmitBottomSheet -> handleOnClickCancelSubmitBottomSheet()
            is Intent.OnClickConfirmSubmitBottomSheet -> loadingLaunch { handleOnClickConfirmSubmitBottomSheet() }
        }
    }

    private suspend fun handleInit() {
        val rounds = getWeeklyContestInfoListUseCase
            .invoke()
            .getOrNull()

        if (rounds == null || rounds.weeklyContestInfoList.isEmpty()) {
            postSideEffect(Effect.NavigateToBack)
            return
        }

        reduce {
            copy(
                currentContestInfo = rounds.weeklyContestInfoList.last(),
            )
        }
    }

    private fun handleOpenMediaPicker() {
        postSideEffect(Effect.OpenMediaPicker)
    }

    private fun handleInitMedia(intent: Intent.InitMedia) {
        val uri = intent.mediaUri

        if (uri == null) {
            postSideEffect(Effect.NavigateToBack)
            return
        }

        reduce {
            copy(
                currentMedia = intent.mediaUri,
            )
        }
    }

    private fun handleOnClickBack() {
        showBackDialog()
    }

    private fun handleOnClickCancelBackDialog() {
        dismissBackDialog()
    }

    private fun handleOnClickConfirmBackDialog() {
        dismissBackDialog()
        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnTitleValueChanged(intent: Intent.OnTitleValueChanged) {
        val newValue = intent.newValue

        if (newValue.length > currentState.maxInputLength) {
            return
        }

        reduce {
            copy(
                title = newValue,
            )
        }
    }

    private fun handleOnClickSubmit() {
        val submitData = currentState

        if (submitData.currentMedia == null) {
            analyticsHelper.d { "handleOnClickSubmit - submitData.currentMedia is null" }
            return
        }

        if (submitData.title.isEmpty()) {
            analyticsHelper.d { "handleOnClickSubmit - submitData.title is empty" }
            return
        }

        showSubmitBottomSheet()
    }

    private fun handleOnClickCancelSubmitBottomSheet() {
        dismissSubmitBottomSheet()
    }

    private suspend fun handleOnClickConfirmSubmitBottomSheet() {
        dismissSubmitBottomSheet()

        submitRemote()
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

    private suspend fun submitRemote() {
        val submitData = currentState

        if (submitData.currentMedia == null) {
            analyticsHelper.d { "submitRemote - submitData.currentMedia is null" }
            return
        }

        if (submitData.title.isEmpty()) {
            analyticsHelper.d { "submitRemote - submitData.title is empty" }
            return
        }

        val result = registerPostUseCase(
            params = RegisterPostUseCase.Params(
                title = submitData.title,
                imageFile = submitData.currentMedia.toString(),
            ),
        ).getOrNull()

        if (result == null) {
            analyticsHelper.d { "handleOnClickSubmitRemote - result: $result" }
            postSingleButtonDialogUseCase(
                type = CommonDialogType.NETWORK_ERROR,
            )
            return
        }

        postSideEffect(Effect.NavigateToPost(result))
    }
}
