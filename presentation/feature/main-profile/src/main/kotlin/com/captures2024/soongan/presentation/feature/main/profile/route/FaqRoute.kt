package com.captures2024.soongan.presentation.feature.main.profile.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.profile.component.screen.FaqScreen
import com.captures2024.soongan.presentation.viewmodel.main.profile.FaqViewModel

@Composable
internal fun FaqRoute(
    navigateToBack: () -> Unit,
    viewModel: FaqViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is FaqViewModel.Effect.NavigateToBack -> navigateToBack()
            }
        }
    }

    FaqScreen(
        state = state,
        onClickBack = { viewModel.intent(FaqViewModel.Intent.OnClickBack) },
        onClickCategory = { viewModel.intent(FaqViewModel.Intent.OnClickCategory(it)) },
        onClickInquiry = { viewModel.intent(FaqViewModel.Intent.OnClickInquiry) },
    )
}
