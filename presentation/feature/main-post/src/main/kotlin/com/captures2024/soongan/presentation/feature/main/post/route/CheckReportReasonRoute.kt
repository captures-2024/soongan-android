package com.captures2024.soongan.presentation.feature.main.post.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.presentation.feature.main.post.component.screen.ReportInputReasonScreen
import com.captures2024.soongan.presentation.feature.main.post.component.screen.ReportReasonScreen
import com.captures2024.soongan.presentation.viewmodel.main.post.PostReportViewModel

@Composable
internal fun CheckReportReasonRoute(
    viewModel: PostReportViewModel,
    modifier: Modifier = Modifier,
    navigateToDoneReportReason: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            if (effect is PostReportViewModel.Effect.NavigateToDoneReportReason) {
                navigateToDoneReportReason()
            }
        }
    }

    when (state.selectedReportType) {
        ReportType.COPYRIGHT_OR_PRIVACY_VIOLATION,
        ReportType.OTHER,
        -> ReportInputReasonScreen(
            state = state,
            onReasonValueChanged = { viewModel.intent(PostReportViewModel.Intent.OnReasonValueChanged(it)) },
            onClickSubmit = { viewModel.intent(PostReportViewModel.Intent.OnClickSubmit) },
        )

        else -> ReportReasonScreen(
            state = state,
            modifier = modifier,
            onClickSubmit = { viewModel.intent(PostReportViewModel.Intent.OnClickSubmit) },
        )
    }
}
