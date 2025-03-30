package com.captures2024.soongan.feature.signIn.route

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.captures2024.soongan.core.auth.GoogleApiContract
import com.captures2024.soongan.core.viewmodel.sign.SignViewModel
import com.captures2024.soongan.feature.signIn.ui.SignInScreen
import com.google.android.gms.common.api.ApiException

@Composable
internal fun SignInRoute(
    navigateToNickname: () -> Unit,
    navigateToBirth: (String) -> Unit,
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    signViewModel: SignViewModel,
) {
    val authResultLauncher = rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
        val idToken = runCatching { task?.getResult(ApiException::class.java)?.idToken }.getOrNull()

        when (idToken) {
            null -> Unit

            else -> signViewModel.intent(SignViewModel.Intent.CompleteSignGoogleResult(idToken))
        }
    }

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

                is SignViewModel.Effect.GoogleSignIn -> authResultLauncher.launch(effect.signInRequestCode)
            }
        }
    }

    SignInScreen(intent = signViewModel::intent)
}
