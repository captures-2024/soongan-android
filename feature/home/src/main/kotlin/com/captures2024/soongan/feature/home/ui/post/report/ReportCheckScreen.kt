package com.captures2024.soongan.feature.home.ui.post.report

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.core.viewmodel.report.ReportCheckViewModel
import com.captures2024.soongan.feature.home.state.ReportRouteState

@Composable
internal fun ReportCheckScreen(
    reportRouteState: ReportRouteState,
    navigateToBack: () -> Unit,
    navigateToDone: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    reportCheckViewModel: ReportCheckViewModel = hiltViewModel(),
) {
    val uiState by reportCheckViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        reportCheckViewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is ReportCheckViewModel.Effect.NavigateToDone -> navigateToDone(sideEffect.hasExtraMessage)
            }
        }
    }

    fun onClickSubmit() {
        reportCheckViewModel.intent(
            ReportCheckViewModel.Intent.OnClickSubmitButton(
                targetId = reportRouteState.targetId,
                targetType = reportRouteState.targetType
            )
        )
    }

    if(uiState.isError) {
        ReportErrorScreen(
            onClickButton = navigateToBack
        )
    } else {
        when (uiState.reportType) {
            ReportType.COPYRIGHT_OR_PRIVACY_VIOLATION, ReportType.OTHER -> {
                ReportReasonScreen(
                    reason = uiState.reason,
                    modifier = modifier,
                    onReasonChanged = { reportCheckViewModel.intent(ReportCheckViewModel.Intent.OnReasonChanged(it)) },
                    onClickSubmit = { onClickSubmit() }
                )
            }

            else -> {
                ReportNoReasonScreen(
                    reportType = uiState.reportType,
                    modifier = modifier,
                    onClickSubmit = { onClickSubmit() }
                )
            }
        }
    }
}