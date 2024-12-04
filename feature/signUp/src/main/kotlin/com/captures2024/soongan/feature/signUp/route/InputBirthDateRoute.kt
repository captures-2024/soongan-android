package com.captures2024.soongan.feature.signUp.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.feature.signUp.BirthDateViewModel
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthDateIntent
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthDateSideEffect
import com.captures2024.soongan.feature.signUp.ui.InputBirthYearScreen

@Composable
internal fun InputBirthDateRoute(
    navigateToBack: () -> Unit,
    navigateToMain: () -> Unit,
    birthDateViewModel: BirthDateViewModel = hiltViewModel()
) {
    val uiState = birthDateViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        birthDateViewModel.sideEffect.collect {
            when (it) {
                is BirthDateSideEffect.NavigateToBack -> navigateToBack()

                is BirthDateSideEffect.NavigateToMain -> navigateToMain()
            }
        }
    }

    InputBirthYearScreen(
        state = uiState.value,
        onClickBack = { birthDateViewModel.intent(BirthDateIntent.OnClickBack) },
        onValueChange = { birthDateViewModel.intent(BirthDateIntent.OnValueChanged(it)) }
    )
}