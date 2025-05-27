package com.captures2024.soongan.presentation.feature.main.post.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.captures2024.soongan.presentation.feature.main.post.component.screen.SelectReportReasonScreen
import com.captures2024.soongan.presentation.viewmodel.main.post.PostReportViewModel

@Composable
internal fun SelectReportReasonRoute(
    viewModel: PostReportViewModel,
    modifier: Modifier = Modifier,
    navigateToCheckReportType: () -> Unit,
) {
    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            if (effect is PostReportViewModel.Effect.NavigateToCheckReportType) {
                navigateToCheckReportType()
            }
        }
    }

    SelectReportReasonScreen(
        modifier = modifier,
        onClickReportType = { viewModel.intent(PostReportViewModel.Intent.OnClickReportType(it)) },
    )
}
