package com.captures2024.soongan.presentation.feature.main.post.route

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.captures2024.soongan.core.model.utils.ReportType
import com.captures2024.soongan.presentation.feature.main.post.component.screen.DoneReportReasonScreen
import com.captures2024.soongan.presentation.viewmodel.main.post.PostReportViewModel

@Composable
internal fun DoneReportReasonRoute(
    viewModel: PostReportViewModel,
    navigateToHidePost: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            if (effect is PostReportViewModel.Effect.NavigateToHidePost) {
                navigateToHidePost()
            }
        }
    }

    BackHandler {}

    val hasExtraMessage = when (state.selectedReportType) {
        ReportType.COPYRIGHT_OR_PRIVACY_VIOLATION,
        ReportType.OTHER,
        -> true

        else -> false
    }

    DoneReportReasonScreen(
        hasExtraMessage = hasExtraMessage,
        onClickConfirm = { viewModel.intent(PostReportViewModel.Intent.OnClickDoneReport) },
    )
}
