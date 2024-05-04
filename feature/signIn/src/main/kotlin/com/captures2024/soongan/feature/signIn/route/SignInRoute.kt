package com.captures2024.soongan.feature.signIn.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavOptions
import com.captures2024.soongan.feature.signIn.SignInState
import com.captures2024.soongan.feature.signIn.SignInViewModel
import com.captures2024.soongan.feature.signIn.ui.SignInDefaultScreen
import com.captures2024.soongan.feature.signIn.ui.SignInLoadingScreen

@Composable
internal fun SignInRoute(
    navigateToTermsOfUse: (NavOptions) -> Unit,
    navigateToPrivacyPolicy: (NavOptions) -> Unit,
    signInViewModel: SignInViewModel = hiltViewModel()
) {
    val uiState = signInViewModel.uiState.collectAsState()

    when (uiState.value) {
        is SignInState.Loading -> SignInLoadingScreen()
        else -> SignInDefaultScreen(
            onClickGoogleSignIn = signInViewModel::onClickGoogleSignIn,
            onClickKakaoSignIn = signInViewModel::onClickKakaoSignIn,
            navigateToTermsOfUse = navigateToTermsOfUse,
            navigateToPrivacyPolicy = navigateToPrivacyPolicy
        )
    }
}