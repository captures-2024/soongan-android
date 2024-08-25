package com.captures2024.soongan.feature.signIn.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.captures2024.soongan.feature.signIn.SignInViewModel
import com.captures2024.soongan.feature.signIn.state.SignInIntent
import com.captures2024.soongan.feature.signIn.state.SignInSideEffect
import com.captures2024.soongan.feature.signIn.ui.SignInDefaultScreen
import com.captures2024.soongan.feature.signIn.ui.SignInLoadingScreen
import timber.log.Timber

@Composable
internal fun SignInRoute(
    appleSignIn: () -> Unit,
    googleSignIn: () -> Unit,
    kakaoSignIn: () -> Unit,
    navigateToMain: (Boolean) -> Unit,
    navigateToNickname: () -> Unit,
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    signInViewModel: SignInViewModel
) {
    val uiState by signInViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        signInViewModel.sideEffect.collect {
            Timber.tag("SignInRoute").d("Collected sideEffect = $it")
            when (it) {
                is SignInSideEffect.AppleSignIn -> appleSignIn()

                is SignInSideEffect.GoogleSignIn -> googleSignIn()

                is SignInSideEffect.KakaoSignIn -> kakaoSignIn()

                is SignInSideEffect.NavigateToMain -> TODO()

                is SignInSideEffect.NavigateToPrivacyPolicy -> navigateToPrivacyPolicy()

                is SignInSideEffect.NavigateToSignUp -> navigateToNickname()

                is SignInSideEffect.NavigateToTermsOfUse -> navigateToTermsOfUse()
            }
        }
    }

    when (uiState.isLoading) {
        true -> SignInLoadingScreen()
        false -> SignInDefaultScreen(
            onClickAppleSignIn = { signInViewModel.intent(SignInIntent.OnClickSignApple) },
            onClickGoogleSignIn = { signInViewModel.intent(SignInIntent.OnClickSignGoogle) },
            onClickKakaoSignIn = { signInViewModel.intent(SignInIntent.OnClickSignKakao) },
            onClickTermsOfUse = { signInViewModel.intent(SignInIntent.OnClickTermsOfUse) },
            onClickGuestMode = { signInViewModel.intent(SignInIntent.OnClickGuestMode) },
            onClickToPrivacyPolicy = { signInViewModel.intent(SignInIntent.OnClickPrivacyPolicy) }
        )
    }

}