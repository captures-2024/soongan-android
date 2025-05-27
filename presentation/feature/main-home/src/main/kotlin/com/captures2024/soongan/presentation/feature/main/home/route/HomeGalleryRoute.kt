package com.captures2024.soongan.presentation.feature.main.home.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.captures2024.soongan.presentation.feature.main.home.component.screen.HomeGalleryScreen
import com.captures2024.soongan.presentation.viewmodel.main.home.HomeGalleryViewModel

@Composable
internal fun HomeGalleryRoute(
    getHidePostId: () -> Long,
    navigateToBack: () -> Unit,
    navigateToPost: (Long) -> Unit,
    navigateToRegistrationPost: () -> Unit,
    viewModel: HomeGalleryViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is HomeGalleryViewModel.Effect.NavigateToBack -> navigateToBack()
                is HomeGalleryViewModel.Effect.NavigateToPost -> navigateToPost(effect.postId)
                is HomeGalleryViewModel.Effect.NavigateToRegisterPost -> navigateToRegistrationPost()
            }
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        val postId = getHidePostId()

        if (postId != -1L) {
            viewModel.intent(HomeGalleryViewModel.Intent.HidePost(postId))
        }
    }

    HomeGalleryScreen(
        state = state,
        onClickBack = { viewModel.intent(HomeGalleryViewModel.Intent.OnClickBack) },
        onClickFilter = { viewModel.intent(HomeGalleryViewModel.Intent.OnClickFilter) },
        onRefresh = { viewModel.intent(HomeGalleryViewModel.Intent.OnRefresh) },
        onLoadNextPage = { viewModel.intent(HomeGalleryViewModel.Intent.OnLoadNextPage) },
        onClickPost = { viewModel.intent(HomeGalleryViewModel.Intent.OnClickPost(it)) },
        onClickRegisterPost = { viewModel.intent(HomeGalleryViewModel.Intent.OnClickRegisterPost) },
        onDismissRequestFilterBottomSheet = { viewModel.intent(HomeGalleryViewModel.Intent.OnDismissRequestFilterBottomSheet) },
        onClickFilterItem = { viewModel.intent(HomeGalleryViewModel.Intent.OnClickFilterItem(it)) },
    )
}
