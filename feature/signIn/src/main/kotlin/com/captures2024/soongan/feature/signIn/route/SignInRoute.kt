package com.captures2024.soongan.feature.signIn.route

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    navigateToSignUp: (NavOptions) -> Unit,
    navigateToTermsOfUse: (NavOptions) -> Unit,
    navigateToPrivacyPolicy: (NavOptions) -> Unit,
    signInViewModel: SignInViewModel
) {
    val uiState = signInViewModel.uiState.collectAsStateWithLifecycle()

    Log.d("SignInRoute", "State change SignInRoute ${uiState.value}")

    when (val value = uiState.value) {
        is SignInState.Loading -> SignInLoadingScreen()
        else -> {
            if (value is SignInState.SignUp) {
                signInViewModel.restoreInitState()
                LaunchedEffect(true) {
                    val options = NavOptions.Builder().build()
                    navigateToSignUp(options)
                }
            }

            SignInDefaultScreen(
                onClickAppleSignIn = appleSignIn,
                onClickGoogleSignIn = googleSignIn,
                onClickKakaoSignIn = kakaoSignIn,
                navigateToMain = navigateToMain,
                navigateToTermsOfUse = navigateToTermsOfUse,
                navigateToPrivacyPolicy = navigateToPrivacyPolicy
            )
        }
    }
}