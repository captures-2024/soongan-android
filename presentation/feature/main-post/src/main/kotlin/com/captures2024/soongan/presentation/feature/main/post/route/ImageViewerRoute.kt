package com.captures2024.soongan.presentation.feature.main.post.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.post.component.screen.ImageViewerScreen
import com.captures2024.soongan.presentation.viewmodel.main.post.ImageViewerViewModel

@Composable
internal fun ImageViewerRoute(
    navigateToBack: () -> Unit,
    viewModel: ImageViewerViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is ImageViewerViewModel.Effect.NavigateToBack -> navigateToBack()
            }
        }
    }

    ImageViewerScreen(
        state = state,
        onClickBack = { viewModel.intent(ImageViewerViewModel.Intent.OnClickBack) },
    )
}
