package com.captures2024.soongan.feature.signIn.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.viewmodel.sign.SignViewModel
import com.captures2024.soongan.feature.signIn.ui.SignInDefaultScreen
import com.captures2024.soongan.feature.signIn.ui.SignInLoadingScreen

@Composable
internal fun SignInRoute(
    navigateToNickname: () -> Unit,
    navigateToBirth: (String) -> Unit,
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    signViewModel: SignViewModel,
) {
    val analyticsHelper = LocalAnalyticsHelper.current
    val uiState by signViewModel.state.collectAsStateWithLifecycle()

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

    when (uiState.isLoading) {
        true -> SignInLoadingScreen()

        false -> SignInDefaultScreen(
            onClickGoogleSignIn = { TODO("onClickGoogleSignIn Not impl yet") },
            onClickKakaoSignIn = { signViewModel.intent(SignViewModel.Intent.OnClickSignKakao) },
            onClickTermsOfUse = { signViewModel.intent(SignViewModel.Intent.OnClickTermsOfUse) },
            onClickGuestMode = { signViewModel.intent(SignViewModel.Intent.OnClickGuestMode) },
            onClickToPrivacyPolicy = { signViewModel.intent(SignViewModel.Intent.OnClickPrivacyPolicy) }
        )
    }
}
