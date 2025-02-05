package com.captures2024.soongan.feature.signIn.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
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
    val analyticsHelper = LocalAnalyticsHelper.current

    LaunchedEffect(Unit) {
        signViewModel.sideEffect.collect {
            analyticsHelper.d(message = "Collected sideEffect = $it")
            when (it) {
                is SignViewModel.Effect.NavigateToTermsOfUse -> navigateToTermsOfUse()

                is SignViewModel.Effect.NavigateToPrivacyPolicy -> navigateToPrivacyPolicy()

                is SignViewModel.Effect.NavigateToSignUp -> {
                    val nickname = it.nickname

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
            }
        }
    }

    SignInScreen(intent = signViewModel::intent)
}
