package com.captures2024.soongan.feature.signUp.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.feature.signUp.BirthYearViewModel
import com.captures2024.soongan.feature.signUp.ui.InputBirthYearScreen

@Composable
internal fun InputBirthYearRoute(
    nickname: String,
    navigateToBack: () -> Unit,
    navigateToHome: () -> Unit,
    birthYearViewModel: BirthYearViewModel = hiltViewModel()
) {
    val uiState = birthYearViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        birthYearViewModel.initNickname(nickname)
    }

    InputBirthYearScreen(
        state = uiState.value,
        onClickBack = navigateToBack,
        onValueChange = birthYearViewModel::onValueChange
    )
}