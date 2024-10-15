package com.captures2024.soongan.feature.signUp.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.feature.signUp.BirthYearViewModel
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthDateIntent
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthDateSideEffect
import com.captures2024.soongan.feature.signUp.ui.InputBirthYearScreen

@Composable
internal fun InputBirthYearRoute(
    navigateToBack: () -> Unit,
    navigateToMain: () -> Unit,
    birthYearViewModel: BirthYearViewModel = hiltViewModel()
) {
    val uiState = birthYearViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        birthYearViewModel.sideEffect.collect {
            when (it) {
                is BirthDateSideEffect.NavigateToBack -> navigateToBack()

                is BirthDateSideEffect.NavigateToMain -> navigateToMain()
            }
        }
    }

    InputBirthYearScreen(
        state = uiState.value,
        onClickBack = { birthYearViewModel.intent(BirthDateIntent.OnClickBack) },
        onValueChange = { birthYearViewModel.intent(BirthDateIntent.OnValueChanged(it)) }
    )
}