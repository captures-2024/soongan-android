package com.captures2024.soongan.presentation.feature.main.profile.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.presentation.feature.main.profile.component.screen.ExplainScreen
import com.captures2024.soongan.presentation.viewmodel.main.profile.ExplainViewModel

@Composable
internal fun ExplainRoute(
    navigateToBack: () -> Unit,
    explainViewModel: ExplainViewModel = hiltViewModel(),
) {
    val state by explainViewModel.state.collectAsState()

    LaunchedEffect(explainViewModel.sideEffect) {
        explainViewModel.sideEffect.collect { effect ->
            when (effect) {
                is ExplainViewModel.Effect.NavigateToBack -> navigateToBack()
            }
        }
    }

    ExplainScreen(
        state = state,
        onClickBack = { explainViewModel.intent(ExplainViewModel.Intent.OnClickBack) },
        onExplainValueChange = { explainViewModel.intent(ExplainViewModel.Intent.OnExplainValueChange(it)) },
        onClickReport = { explainViewModel.intent(ExplainViewModel.Intent.OnClickReport) },
    )
}
