package com.captures2024.soongan.feature.signIn.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.captures2024.soongan.core.auth.requestGoogleLogin
import com.captures2024.soongan.core.viewmodel.sign.SignViewModel
import com.captures2024.soongan.feature.signIn.ui.SignInScreen

@Composable
internal fun SignInRoute(
    navigateToNickname: () -> Unit,
    navigateToBirth: (String) -> Unit,
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    signViewModel: SignViewModel,
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        signViewModel.sideEffect.collect { effect ->
            when (effect) {
                is SignViewModel.Effect.NavigateToTermsOfUse -> navigateToTermsOfUse()

                is SignViewModel.Effect.NavigateToPrivacyPolicy -> navigateToPrivacyPolicy()

                is SignViewModel.Effect.NavigateToSignUp -> {
                    val nickname = effect.nickname

                    if (nickname == null) {
                        navigateToNickname()
                        return@collect
                    }

                    when (nickname.isEmpty()) {
                        true -> navigateToNickname()

                        false -> navigateToBirth(nickname)
                    }
                }

                is SignViewModel.Effect.KakaoSignIn -> Unit

                is SignViewModel.Effect.GoogleSignIn -> signViewModel.intent(SignViewModel.Intent.CompleteSignGoogleResult(context.requestGoogleLogin()))
            }
        }
    }

    SignInScreen(intent = signViewModel::intent)
}
