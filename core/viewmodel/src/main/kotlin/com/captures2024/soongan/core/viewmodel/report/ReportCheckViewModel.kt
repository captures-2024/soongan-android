package com.captures2024.soongan.core.viewmodel.report

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.common.base.BaseViewModel
import com.captures2024.soongan.core.common.base.UIIntent
import com.captures2024.soongan.core.common.base.UISideEffect
import com.captures2024.soongan.core.common.base.UIState
import com.captures2024.soongan.core.domain.usecase.report.PostReportUseCase
import com.captures2024.soongan.core.model.utils.ReportTargetType
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.core.navigator.screen.main.report.ReportNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReportCheckViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val postReportUseCase: PostReportUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ReportCheckViewModel.State, ReportCheckViewModel.Effect, ReportCheckViewModel.Intent>(
    savedStateHandle = savedStateHandle
) {

    data class State(
        val isLoading: Boolean = false,
        val reportType: ReportType = ReportType.INAPPROPRIATE_PHOTO_OR_BEHAVIOR,
        val reason: String = "",
        val isError: Boolean = false,
    ) : UIState {
        override fun toLoggingElements(): Array<LogElementArgument> = arrayOf(
            LogElementArgument("isLoading", isLoading.toString()),
            LogElementArgument("reportType", reportType.toString()),
            LogElementArgument("reason", reason),
            LogElementArgument("isError", isError.toString()),
        )
    }

    sealed interface Effect : UISideEffect {

        data object NavigateToDone : Effect
    }

    sealed interface Intent : UIIntent {

        data class OnReasonChanged(
            val reason: String,
        ) : Intent

        data class OnClickSubmitButton(
            val targetId: Long,
            val targetType: ReportTargetType,
        ) : Intent
    }

    override fun createInitialState(savedStateHandle: SavedStateHandle): State {
        val info = savedStateHandle.toRoute<ReportNavigator.Check>()
        val reportType = ReportType.entries.find { it.name == info.reportType }

        return when (reportType) {
            null -> State(isError = true)

            else -> State(reportType = reportType)
        }
    }

    override fun handleClientException(throwable: Throwable) {
        analyticsHelper.e(
            throwable = throwable,
            logVariable = currentState.toLoggingElements(),
            message = "handleClientException"
        )
    }

    override suspend fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnReasonChanged -> onReasonChanged(intent)

            is Intent.OnClickSubmitButton -> postReport(intent)
        }
    }

    private fun onReasonChanged(intent: Intent.OnReasonChanged) {
        reduce {
            copy(
                reason = intent.reason
            )
        }
    }

    private suspend fun postReport(intent: Intent.OnClickSubmitButton) {

        val result = postReportUseCase(
            params = PostReportUseCase.Params(
                targetId = intent.targetId,
                targetType = intent.targetType,
                reportType = currentState.reportType,
                reason = currentState.reason,
            )
        ).getOrNull()

        analyticsHelper.d(message = "report result = $result")

        if (result != true) {
            reduce {
                copy(isError = true)
            }

            return
        }

        postSideEffect(Effect.NavigateToDone)
    }
}