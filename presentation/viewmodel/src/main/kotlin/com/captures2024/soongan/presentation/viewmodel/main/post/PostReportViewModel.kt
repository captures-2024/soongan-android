package com.captures2024.soongan.presentation.viewmodel.main.post

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
import com.captures2024.soongan.core.domain.usecase.report.PostReportUseCase
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.core.model.enums.CommonDialogType
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostReportViewModel
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
    private val postReportUseCase: PostReportUseCase,
) : BaseViewModel<PostReportViewModel.State, PostReportViewModel.Effect, PostReportViewModel.Intent>(
    analyticsHelper = analyticsHelper,
    showLoadingUseCase = showLoadingUseCase,
    hideLoadingUseCase = hideLoadingUseCase,
    clearLoadingUseCase = clearLoadingUseCase,
    getIsCurrentGuestModeUseCase = getIsCurrentGuestModeUseCase,
    setIsShowGuestModeDialogFlowUseCase = setIsShowGuestModeDialogFlowUseCase,
    savedStateHandle = savedStateHandle,
) {

    data class State(
        val id: Long,
        val selectedReportType: ReportType,
        val reason: String,
        val maxReasonLength: Int,
    ) : UIState

    sealed interface Effect : UISideEffect {
        data object NavigateToCheckReportType : Effect

        data object NavigateToDoneReportReason : Effect

        data object NavigateToHidePost : Effect
    }

    sealed interface Intent : UIIntent {
        data class InitId(
            val id: Long,
        ) : Intent

        data class OnClickReportType(
            val type: ReportType,
        ) : Intent

        data class OnReasonValueChanged(
            val newValue: String,
        ) : Intent

        data object OnClickSubmit : Intent

        data object OnClickDoneReport : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State = State(
        id = -1L,
        selectedReportType = ReportType.OTHER,
        reason = "",
        maxReasonLength = AppConst.Main.Post.REPORT_REASON_MAX_LENGTH,
    )

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.InitId -> handleInitId(intent)
            is Intent.OnClickReportType -> handleOnClickReportType(intent)
            is Intent.OnReasonValueChanged -> handleOnReasonValueChanged(intent)
            is Intent.OnClickSubmit -> loadingLaunch { handleOnClickSubmit() }
            is Intent.OnClickDoneReport -> handleOnClickDoneReport()
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(throwable) { "state: $currentState" }
    }

    private fun handleInitId(intent: Intent.InitId) {
        reduce {
            copy(
                id = intent.id,
            )
        }
    }

    private fun handleOnClickReportType(intent: Intent.OnClickReportType) {
        reduce {
            copy(
                selectedReportType = intent.type,
            )
        }

        postSideEffect(Effect.NavigateToCheckReportType)
    }

    private fun handleOnReasonValueChanged(intent: Intent.OnReasonValueChanged) {
        if (intent.newValue.length > currentState.maxReasonLength) {
            return
        }

        reduce {
            copy(
                reason = intent.newValue,
            )
        }
    }

    private suspend fun handleOnClickSubmit() {
        val state = currentState

        if (state.id == -1L) {
            return
        }

        val result = postReportUseCase(
            params = PostReportUseCase.Params(
                targetId = state.id,
                targetType = ReportTargetType.WEEKLY_POST,
                reportType = state.selectedReportType,
                reason = state.reason,
            ),
        ).getOrNull()

        if (result != true) {
            postSingleButtonDialogUseCase(CommonDialogType.NETWORK_ERROR)
            return
        }

        postSideEffect(Effect.NavigateToDoneReportReason)
    }

    private fun handleOnClickDoneReport() {
        postSideEffect(Effect.NavigateToHidePost)
    }
}
