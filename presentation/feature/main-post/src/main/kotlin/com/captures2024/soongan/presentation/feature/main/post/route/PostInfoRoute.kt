package com.captures2024.soongan.presentation.feature.main.post.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.captures2024.soongan.presentation.feature.main.post.component.screen.PostInfoScreen
import com.captures2024.soongan.presentation.viewmodel.main.post.PostInfoViewModel

@Composable
internal fun PostInfoRoute(
    navigateToBack: () -> Unit,
    navigateToEditPost: (Long, String, String) -> Unit,
    navigateToImageViewer: (String) -> Unit,
    viewModel: PostInfoViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is PostInfoViewModel.Effect.NavigateToBack -> navigateToBack()
                is PostInfoViewModel.Effect.NavigateToEditPost -> navigateToEditPost(effect.postId, effect.url, effect.title)
                is PostInfoViewModel.Effect.NavigateToImageViewer -> navigateToImageViewer(effect.url)
            }
        }
    }

    LifecycleResumeEffect(Unit) {
        viewModel.intent(PostInfoViewModel.Intent.OnResumeView)

        onPauseOrDispose {}
    }

    PostInfoScreen(
        state = state,
        onClickBack = { viewModel.intent(PostInfoViewModel.Intent.OnClickBack) },
        onClickMenu = { viewModel.intent(PostInfoViewModel.Intent.OnClickMenu) },
        onClickHeart = { viewModel.intent(PostInfoViewModel.Intent.OnClickHeart) },
        onClickPhoto = { viewModel.intent(PostInfoViewModel.Intent.OnClickPhoto) },
        onDismissRequestMenuBottomSheet = { viewModel.intent(PostInfoViewModel.Intent.OnDismissRequestMenuBottomSheet) },
        onClickDelete = { viewModel.intent(PostInfoViewModel.Intent.OnClickDelete) },
        onClickEditPost = { viewModel.intent(PostInfoViewModel.Intent.OnClickEditPost) },
        onClickReport = { viewModel.intent(PostInfoViewModel.Intent.OnClickReport) },
        onDismissRequestReportBottomSheet = { viewModel.intent(PostInfoViewModel.Intent.OnDismissRequestReportBottomSheet) },
        onDoneReport = { viewModel.intent(PostInfoViewModel.Intent.OnDoneReport) },
    )
}
