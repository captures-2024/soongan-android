package com.captures2024.soongan.presentation.feature.main.home.route

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.captures2024.soongan.presentation.feature.main.home.component.screen.HomeScreen
import com.captures2024.soongan.presentation.viewmodel.main.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is HomeViewModel.Effect.NavigateToPost -> TODO()
                is HomeViewModel.Effect.NavigateToPostList -> TODO()
                is HomeViewModel.Effect.NavigateToRegister -> TODO()
            }
        }
    }

    LifecycleResumeEffect(Unit) {
        viewModel.intent(HomeViewModel.Intent.ViewOnResume)
        onPauseOrDispose {}
    }

    HomeScreen(
        state = state,
        onClickContestInfo = { viewModel.intent(HomeViewModel.Intent.OnClickContestInfo) },
        onClickPostList = { viewModel.intent(HomeViewModel.Intent.OnClickPostList) },
        onClickRegister = { viewModel.intent(HomeViewModel.Intent.OnClickRegister) },
        onClickPost = { viewModel.intent(HomeViewModel.Intent.OnClickPost(it)) },
        onClickRetry = { viewModel.intent(HomeViewModel.Intent.OnClickRetry) },
        onDismissRequest = { viewModel.intent(HomeViewModel.Intent.DismissContestInfoBottomSheet) }
    )
}
