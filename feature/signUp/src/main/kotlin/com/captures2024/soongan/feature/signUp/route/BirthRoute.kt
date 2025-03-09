package com.captures2024.soongan.feature.signUp.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.viewmodel.sign.BirthViewModel
import com.captures2024.soongan.feature.signUp.ui.BirthScreen

@Composable
internal fun BirthRoute(
    navigateToBack: () -> Unit,
    birthViewModel: BirthViewModel = hiltViewModel(),
) {
    val uiState by birthViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        birthViewModel.sideEffect.collect {
            when (it) {
                is BirthViewModel.Effect.NavigateToBack -> navigateToBack()
            }
        }
    }

    BirthScreen(
        intent = birthViewModel::intent,
        state = uiState,
    )
}
