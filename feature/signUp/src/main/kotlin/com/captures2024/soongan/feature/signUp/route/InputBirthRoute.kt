package com.captures2024.soongan.feature.signUp.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.viewmodel.sign.SignViewModel
import com.captures2024.soongan.feature.signUp.BirthViewModel
import com.captures2024.soongan.feature.signUp.state.birth.BirthIntent
import com.captures2024.soongan.feature.signUp.state.birth.BirthSideEffect
import com.captures2024.soongan.feature.signUp.ui.InputBirthScreen

@Composable
internal fun InputBirthRoute(
    navigateToBack: () -> Unit,
    signViewModel: SignViewModel,
    birthViewModel: BirthViewModel = hiltViewModel(),
) {
    val uiState = birthViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        birthViewModel.sideEffect.collect {
            when (it) {
                is BirthSideEffect.NavigateToBack -> navigateToBack()

                is BirthSideEffect.NavigateToMain ->
                    signViewModel.intent(
                        SignViewModel.Intent.SuccessPathBirth(
                            nickname = it.nickname,
                            birthYear = it.birthYear,
                        )
                    )
            }
        }
    }

    InputBirthScreen(
        state = uiState.value,
        onClickBack = { birthViewModel.intent(BirthIntent.OnClickBack) },
        onValueChange = { birthViewModel.intent(BirthIntent.OnValueChanged(it)) },
        onClickConfirm = { birthViewModel.intent(BirthIntent.OnClickConfirm) }
    )
}