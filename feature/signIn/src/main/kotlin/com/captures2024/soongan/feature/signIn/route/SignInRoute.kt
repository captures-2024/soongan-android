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
    navigateToSignUp: (NavOptions) -> Unit,
    navigateToTermsOfUse: (NavOptions) -> Unit,
    navigateToPrivacyPolicy: (NavOptions) -> Unit,
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

                SignInSideEffect.NavigateToMain -> TODO()

                SignInSideEffect.NavigateToPrivacyPolicy -> TODO()

                SignInSideEffect.NavigateToSignUp -> TODO()

                SignInSideEffect.NavigateToTermsOfUse -> TODO()
            }
        }
    }

    when (uiState.isLoading) {
        true -> SignInLoadingScreen()
        false -> SignInDefaultScreen(
            onClickAppleSignIn = { signInViewModel.intent(SignInIntent.OnClickSignApple) },
            onClickGoogleSignIn = { signInViewModel.intent(SignInIntent.OnClickSignGoogle) },
            onClickKakaoSignIn = { signInViewModel.intent(SignInIntent.OnClickSignKakao) },
            navigateToMain = {},
            navigateToTermsOfUse = {},
            navigateToPrivacyPolicy = {}
        )
    }

}