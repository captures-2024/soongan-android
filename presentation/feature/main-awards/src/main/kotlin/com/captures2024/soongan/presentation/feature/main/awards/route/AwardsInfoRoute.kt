package com.captures2024.soongan.presentation.feature.main.awards.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.awards.component.screen.AwardsInfoScreen
import com.captures2024.soongan.presentation.viewmodel.main.award.AwardsInfoViewModel

@Composable
internal fun AwardsInfoRoute(
    navigateToBack: () -> Unit,
    navigateToPost: (postId: Long) -> Unit,
    navigateToFeed: () -> Unit,
    viewModel: AwardsInfoViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is AwardsInfoViewModel.Effect.NavigateToBack -> navigateToBack()

                is AwardsInfoViewModel.Effect.NavigateToPost -> navigateToPost(effect.postId)

                is AwardsInfoViewModel.Effect.NavigateToFeed -> navigateToFeed()
            }
        }
    }

    AwardsInfoScreen(
        state = state,
        onClickBack = { viewModel.intent(AwardsInfoViewModel.Intent.OnClickBack) },
        onClickPost = { viewModel.intent(AwardsInfoViewModel.Intent.OnClickPost(it)) },
        onClickAllPosts = { viewModel.intent(AwardsInfoViewModel.Intent.OnClickAllPosts) },
        onClickRetry = { viewModel.intent(AwardsInfoViewModel.Intent.OnClickRetry) },
    )
}
