package com.captures2024.soongan.presentation.feature.main.awards.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.awards.component.screen.AwardsScreen
import com.captures2024.soongan.presentation.viewmodel.main.award.AwardsViewModel

@Composable
internal fun AwardsRoute(
    navigateToAwardsInfo: (round: Int) -> Unit,
    viewModel: AwardsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is AwardsViewModel.Effect.NavigateToAwardsInfo -> navigateToAwardsInfo(effect.round)
            }
        }
    }

    AwardsScreen(
        state = state,
        onClickContestSubject = { viewModel.intent(AwardsViewModel.Intent.OnClickContestSubject(it)) },
        onClickRetry = { viewModel.intent(AwardsViewModel.Intent.OnClickEntry) }
    )
}
