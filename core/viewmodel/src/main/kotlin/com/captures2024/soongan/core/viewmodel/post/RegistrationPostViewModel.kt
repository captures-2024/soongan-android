package com.captures2024.soongan.core.viewmodel.post

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.loading.ClearLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.HideLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.loading.ShowLoadingUseCase
import com.captures2024.soongan.core.domain.usecase.weekly.contests.RegisterPostUseCase
import com.captures2024.soongan.core.viewmodel.NewBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationPostViewModel
@Inject
constructor(
    private val registerPostUseCase: RegisterPostUseCase,
    analyticsHelper: AnalyticsHelper,
    showLoadingUseCase: ShowLoadingUseCase,
    hideLoadingUseCase: HideLoadingUseCase,
    clearLoadingUseCase: ClearLoadingUseCase,
    savedStateHandle: SavedStateHandle
) : NewBaseViewModel<RegistrationPostViewModel.State, RegistrationPostViewModel.Effect, RegistrationPostViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    savedStateHandle = savedStateHandle,
) {
    data class State(
        val currentMedia: Uri? = null,
        val title: String = "",
        val isOpenSubmitBottomSheet: Boolean = false,
        val showBackDialog: Boolean = false,
    ) : UIState {

        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("currentMedia", currentMedia.toString()),
            LogElementArgument("title", title),
            LogElementArgument("isOpenSubmitBottomSheet", isOpenSubmitBottomSheet.toString()),
            LogElementArgument("showBackDialog", showBackDialog.toString()),
        )
    }

    sealed interface Effect : UISideEffect {

        data object OpenMediaPicker : Effect

        data object NavigateToBack : Effect

        data class NavigateToPost(
            val postId: Int,
        ) : Effect
    }

    sealed interface Intent : UIIntent {

        data object Init : Intent

        data class InitMedia(
            val mediaUri: Uri?,
        ) : Intent

        data class OnTitleValueChanged(
            val newValue: String,
        ) : Intent

        data object OnClickBack : Intent

        data object OnClickSubmit : Intent

        data object OnClickCloseBottomSheet : Intent

        data object OnClickSubmitRemote : Intent

        data object OnClickBackDialogConfirm : Intent

        data object OnClickBackDialogCancel : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State()

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
            message = "handleClientException"
        )
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> handleInit()

            is Intent.InitMedia -> handleInitMedia(intent)

            is Intent.OnClickBack -> handleOnClickBack()

            is Intent.OnClickBackDialogCancel -> handleOnClickBackDialogCancel()

            is Intent.OnClickBackDialogConfirm -> handleOnClickBackDialogConfirm()

            is Intent.OnClickCloseBottomSheet -> handleOnClickCloseBottomSheet()

            is Intent.OnClickSubmit -> handleOnClickSubmit()

            is Intent.OnClickSubmitRemote -> loadingLaunch { handleOnClickSubmitRemote() }

            is Intent.OnTitleValueChanged -> handleOnTitleValueChanged(intent)
        }
    }

    private fun handleInit() {
        postSideEffect(Effect.OpenMediaPicker)
    }

    private fun handleInitMedia(intent: Intent.InitMedia) {
        val uri = intent.mediaUri

        if (uri == null) {
            postSideEffect(Effect.NavigateToBack)
            return
        }

        reduce {
            copy(currentMedia = intent.mediaUri)
        }
    }

    private fun handleOnClickBack() {
        reduce {
            copy(
                showBackDialog = true,
            )
        }
    }

    private fun handleOnClickBackDialogCancel() {
        reduce {
            copy(
                showBackDialog = false,
            )
        }
    }

    private fun handleOnClickBackDialogConfirm() {
        reduce {
            copy(
                showBackDialog = false,
            )
        }

        postSideEffect(Effect.NavigateToBack)
    }

    private fun handleOnClickCloseBottomSheet() {
        reduce {
            copy(
                isOpenSubmitBottomSheet = false,
            )
        }
    }

    private fun handleOnClickSubmit() {
        val submitData = currentState

        if (submitData.currentMedia == null) {
            analyticsHelper.d(message = "handleOnClickSubmit - submitData.currentMedia is null")
            return
        }

        if (submitData.title.isEmpty()) {
            analyticsHelper.d(message = "handleOnClickSubmit - submitData.title is empty")
            return
        }

        reduce {
            copy(
                isOpenSubmitBottomSheet = true,
            )
        }
    }

    private suspend fun handleOnClickSubmitRemote() {
        val submitData = currentState

        if (submitData.currentMedia == null) {
            analyticsHelper.d(message = "handleOnClickSubmitRemote - submitData.currentMedia is null")
            return
        }

        if (submitData.title.isEmpty()) {
            analyticsHelper.d(message = "handleOnClickSubmitRemote - submitData.title is empty")
            return
        }

        val result = registerPostUseCase(
            params = RegisterPostUseCase.Params(
                weeklyContestRound = 1,
                subject = submitData.title,
                imageFile = submitData.currentMedia.toString(),
            )
        ).getOrNull() ?: -1

        if (result == -1) {
            analyticsHelper.d(message = "handleOnClickSubmitRemote - result: $result")
            return
        }

        postSideEffect(Effect.NavigateToPost(result))
    }

    private fun handleOnTitleValueChanged(intent: Intent.OnTitleValueChanged) {
        val newValue = intent.newValue

        if (newValue.length !in 0 .. 15) {
            return
        }

        reduce {
            copy(title = newValue)
        }
    }
}
