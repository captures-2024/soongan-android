package com.captures2024.soongan.feature.signIn.route

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavOptions
import com.captures2024.soongan.feature.signIn.SignInState
import com.captures2024.soongan.feature.signIn.SignInViewModel
import com.captures2024.soongan.feature.signIn.ui.SignInDefaultScreen
import com.captures2024.soongan.feature.signIn.ui.SignInLoadingScreen

@Composable
internal fun SignInRoute(
    appleSignIn: () -> Unit,
    googleSignIn: () -> Unit,
    kakaoSignIn: () -> Unit,
    navigateToMain: (Boolean) -> Unit,
    navigateToTermsOfUse: (NavOptions) -> Unit,
    navigateToPrivacyPolicy: (NavOptions) -> Unit,
    signInViewModel: SignInViewModel
) {
    val uiState = signInViewModel.uiState.collectAsState()

    Log.d("SignInRoute", "State change SignInRoute ${uiState.value}")

    when (uiState.value) {
        is SignInState.Loading -> SignInLoadingScreen()
        else -> SignInDefaultScreen(
            onClickAppleSignIn = appleSignIn,
            onClickGoogleSignIn = googleSignIn,
            onClickKakaoSignIn =  kakaoSignIn,
            navigateToMain = navigateToMain,
            navigateToTermsOfUse = navigateToTermsOfUse,
            navigateToPrivacyPolicy = navigateToPrivacyPolicy
        )
    }
}