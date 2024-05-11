package com.captures2024.soongan.feature.signIn.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavOptions
import com.captures2024.soongan.feature.signIn.SignInState
import com.captures2024.soongan.feature.signIn.SignInViewModel
import com.captures2024.soongan.feature.signIn.ui.SignInDefaultScreen
import com.captures2024.soongan.feature.signIn.ui.SignInLoadingScreen

@Composable
internal fun SignInRoute(
    googleSignIn: () -> Unit,
    navigateToTermsOfUse: (NavOptions) -> Unit,
    navigateToPrivacyPolicy: (NavOptions) -> Unit,
    signInViewModel: SignInViewModel
) {
    val uiState = signInViewModel.uiState.collectAsState()

    when (uiState.value) {
        is SignInState.Loading -> SignInLoadingScreen()
        else -> SignInDefaultScreen(
            onClickGoogleSignIn = googleSignIn,
            onClickKakaoSignIn = signInViewModel::onClickKakaoSignIn,
            navigateToTermsOfUse = navigateToTermsOfUse,
            navigateToPrivacyPolicy = navigateToPrivacyPolicy
        )
    }
}