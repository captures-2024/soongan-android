package com.captures2024.soongan.feature.signIn.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.feature.signIn.SignInViewModel
import com.captures2024.soongan.feature.signIn.state.SignInIntent
import com.captures2024.soongan.feature.signIn.state.SignInSideEffect
import com.captures2024.soongan.feature.signIn.ui.SignInDefaultScreen
import com.captures2024.soongan.feature.signIn.ui.SignInLoadingScreen

@Composable
internal fun SignInRoute(
    navigateToNickname: () -> Unit,
    navigateToBirthDate: (String) -> Unit,
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    signInViewModel: SignInViewModel
) {
    val analyticsHelper = LocalAnalyticsHelper.current
    val uiState by signInViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        signInViewModel.sideEffect.collect {
            analyticsHelper.d(message = "Collected sideEffect = $it")
            when (it) {
                is SignInSideEffect.NavigateToTermsOfUse -> navigateToTermsOfUse()

                is SignInSideEffect.NavigateToPrivacyPolicy -> navigateToPrivacyPolicy()

                is SignInSideEffect.NavigateToSignUp -> when (it.nickname.isEmpty()) {
                    true -> navigateToNickname()

                    false -> navigateToBirthDate(it.nickname)
                }

                is SignInSideEffect.GoogleSignIn,
                is SignInSideEffect.KakaoSignIn,
                is SignInSideEffect.SuccessSocialSign -> Unit
            }
        }
    }

    when (uiState.isLoading) {
        true -> SignInLoadingScreen()

        false -> SignInDefaultScreen(
            onClickGoogleSignIn = { signInViewModel.intent(SignInIntent.OnClickSignGoogle) },
            onClickKakaoSignIn = { signInViewModel.intent(SignInIntent.OnClickSignKakao) },
            onClickTermsOfUse = { signInViewModel.intent(SignInIntent.OnClickTermsOfUse) },
            onClickGuestMode = { TODO("Not Impl") },
            onClickToPrivacyPolicy = { signInViewModel.intent(SignInIntent.OnClickPrivacyPolicy) }
        )
    }

}