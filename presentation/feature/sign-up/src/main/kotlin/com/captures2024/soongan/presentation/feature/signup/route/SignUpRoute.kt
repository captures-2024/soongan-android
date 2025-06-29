package com.captures2024.soongan.presentation.feature.signup.route

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.presentation.feature.signup.component.screen.SignUpScreen
import com.captures2024.soongan.presentation.viewmodel.sign.SignUpViewModel

@Composable
internal fun SignUpRoute(
    navigateToBack: () -> Unit,
    signUpViewModel: SignUpViewModel = hiltViewModel(),
) {
    val state by signUpViewModel.state.collectAsStateWithLifecycle()

    BackHandler {
        signUpViewModel.intent(SignUpViewModel.Intent.OnClickBack)
    }

    LaunchedEffect(signUpViewModel.sideEffect) {
        signUpViewModel.sideEffect.collect { effect ->
            when (effect) {
                is SignUpViewModel.Effect.NavigateToBack -> navigateToBack()
            }
        }
    }

    SignUpScreen(
        state = state,
        onClickBack = { signUpViewModel.intent(SignUpViewModel.Intent.OnClickBack) },
        onNicknameValueChanged = {
            signUpViewModel.intent(
                SignUpViewModel.Intent.OnNicknameValueChanged(it),
            )
        },
        onBirthValueChanged = { signUpViewModel.intent(SignUpViewModel.Intent.OnBirthValueChanged(it)) },
        onConfirmNickname = { signUpViewModel.intent(SignUpViewModel.Intent.OnConfirmNickname) },
        onConfirmBirth = { signUpViewModel.intent(SignUpViewModel.Intent.OnConfirmBirth) },
    )
}
