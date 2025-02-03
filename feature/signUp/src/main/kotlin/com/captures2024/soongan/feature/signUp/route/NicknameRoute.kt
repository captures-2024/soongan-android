package com.captures2024.soongan.feature.signUp.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.viewmodel.sign.NicknameViewModel
import com.captures2024.soongan.feature.signUp.ui.NicknameScreen

@Composable
internal fun NicknameRoute(
    navigateToBack: () -> Unit,
    navigateToBirth: (String) -> Unit,
    nicknameViewModel: NicknameViewModel = hiltViewModel(),
) {
    val uiState by nicknameViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        nicknameViewModel.sideEffect.collect {
            when (it) {
                is NicknameViewModel.Effect.NavigateToBack -> navigateToBack()

                is NicknameViewModel.Effect.NavigateToBirth -> navigateToBirth(uiState.nickname)
            }
        }
    }

    NicknameScreen(
        intent = nicknameViewModel::intent,
        state = uiState,
    )
}
