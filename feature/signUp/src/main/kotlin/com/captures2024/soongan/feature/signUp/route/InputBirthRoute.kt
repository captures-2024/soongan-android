package com.captures2024.soongan.feature.signUp.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.viewmodel.SignViewModel
import com.captures2024.soongan.core.viewmodel.intent.SignIntent
import com.captures2024.soongan.feature.signUp.BirthDateViewModel
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthIntent
import com.captures2024.soongan.feature.signUp.state.birthdate.BirthSideEffect
import com.captures2024.soongan.feature.signUp.ui.InputBirthScreen

@Composable
internal fun InputBirthRoute(
    navigateToBack: () -> Unit,
    signViewModel: SignViewModel,
    birthDateViewModel: BirthDateViewModel = hiltViewModel(),
) {
    val uiState = birthDateViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        birthDateViewModel.sideEffect.collect {
            when (it) {
                is BirthSideEffect.NavigateToBack -> navigateToBack()

                is BirthSideEffect.NavigateToMain ->
                    signViewModel.intent(
                        SignIntent.SuccessPathBirth(
                            nickname = it.nickname,
                            birthYear = it.birthYear,
                        )
                    )
            }
        }
    }

    InputBirthScreen(
        state = uiState.value,
        onClickBack = { birthDateViewModel.intent(BirthIntent.OnClickBack) },
        onValueChange = { birthDateViewModel.intent(BirthIntent.OnValueChanged(it)) },
        onClickConfirm = { birthDateViewModel.intent(BirthIntent.OnClickConfirm) }
    )
}