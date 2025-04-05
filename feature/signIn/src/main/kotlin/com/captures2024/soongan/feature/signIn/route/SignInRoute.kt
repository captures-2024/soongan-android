package com.captures2024.soongan.feature.signIn.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.captures2024.soongan.core.auth.kakao.KakaoAuthHelperImpl
import com.captures2024.soongan.core.auth.kakao.KakaoLoginCallback
import com.captures2024.soongan.core.auth.google.requestGoogleLogin
import com.captures2024.soongan.core.viewmodel.sign.SignViewModel
import com.captures2024.soongan.feature.signIn.ui.SignInScreen

@Composable
internal fun SignInRoute(
    navigateToNickname: () -> Unit,
    navigateToBirth: (String) -> Unit,
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    viewModel: SignViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val authHelper = remember { KakaoAuthHelperImpl() }

    val kakaoLoginCallback = remember(viewModel) {
        object : KakaoLoginCallback {
            override fun onSuccessKakaoLogin(
                accessToken: String?,
                refreshToken: String?
            ) {
                viewModel.intent(
                    SignViewModel.Intent.CompleteSignKakao(
                        accessToken = accessToken,
                        refreshToken = refreshToken,
                    ),
                )
            }

            override fun onFailureKakaoLogin(error: Throwable?) {

            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
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

                is SignViewModel.Effect.GoogleSignIn -> viewModel.intent(SignViewModel.Intent.CompleteSignGoogleResult(context.requestGoogleLogin()))
            }
        }
    }

    SignInScreen(
        onClickSignGoogle = { viewModel.intent(SignViewModel.Intent.OnClickSignGoogle) },
        onClickSignKakao = {
            authHelper.kakaoLogin(
                context = context,
                callback = kakaoLoginCallback,
            )
        },
        onClickTermsOfUse = { viewModel.intent(SignViewModel.Intent.OnClickTermsOfUse) },
        onClickPrivacyPolicy = { viewModel.intent(SignViewModel.Intent.OnClickPrivacyPolicy) },
        onClickGuestMode = { viewModel.intent(SignViewModel.Intent.OnClickGuestMode) },
    )
}
