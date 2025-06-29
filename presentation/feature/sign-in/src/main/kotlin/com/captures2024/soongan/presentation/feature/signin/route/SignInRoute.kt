package com.captures2024.soongan.presentation.feature.signin.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.core.auth.google.requestGoogleLogin
import com.captures2024.soongan.core.auth.kakao.KakaoAuthHelperImpl
import com.captures2024.soongan.core.auth.kakao.KakaoLoginCallback
import com.captures2024.soongan.presentation.feature.signin.component.screen.SignInScreen
import com.captures2024.soongan.presentation.viewmodel.sign.SignInViewModel

@Composable
internal fun SignInRoute(
    navigateToSignUp: () -> Unit,
    signInViewModel: SignInViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val authHelper = remember { KakaoAuthHelperImpl() }

    val kakaoLoginCallback = remember(signInViewModel) {
        object : KakaoLoginCallback {
            override fun onSuccessKakaoLogin(
                accessToken: String?,
                refreshToken: String?,
            ) {
                signInViewModel.intent(
                    SignInViewModel.Intent.CompleteSignKakao(
                        accessToken = accessToken,
                        refreshToken = refreshToken,
                    ),
                )
            }

            override fun onFailureKakaoLogin(error: Throwable?) {
                signInViewModel.intent(SignInViewModel.Intent.OnFailedSignKakao(error))
            }
        }
    }

    LaunchedEffect(signInViewModel.sideEffect) {
        signInViewModel.sideEffect.collect { effect ->
            when (effect) {
                is SignInViewModel.Effect.NavigateToSignUp -> navigateToSignUp()
            }
        }
    }

    SignInScreen(
        onClickSignGoogle = {
            signInViewModel.intent(
                intent = SignInViewModel.Intent.OnClickSignGoogle(
                    loginCallback = suspend { context.requestGoogleLogin() },
                ),
            )
        },
        onClickSignKakao = {
            authHelper.kakaoLogin(
                context = context,
                callback = kakaoLoginCallback,
            )
        },
        onClickTerms = { signInViewModel.intent(SignInViewModel.Intent.OnClickTerms) },
        onClickPrivacyPolicy = { signInViewModel.intent(SignInViewModel.Intent.OnClickPrivacyPolicy) },
        onClickGuestMode = { signInViewModel.intent(SignInViewModel.Intent.OnClickGuestMode) },
    )
}
