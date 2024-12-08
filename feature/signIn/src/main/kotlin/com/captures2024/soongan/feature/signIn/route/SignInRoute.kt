package com.captures2024.soongan.feature.signIn.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.viewmodel.SignViewModel
import com.captures2024.soongan.core.viewmodel.effect.SignSideEffect
import com.captures2024.soongan.core.viewmodel.intent.SignIntent
import com.captures2024.soongan.feature.signIn.ui.SignInDefaultScreen
import com.captures2024.soongan.feature.signIn.ui.SignInLoadingScreen

@Composable
internal fun SignInRoute(
    navigateToNickname: () -> Unit,
    navigateToBirthDate: (String) -> Unit,
    navigateToTermsOfUse: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    signViewModel: SignViewModel,
) {
    val analyticsHelper = LocalAnalyticsHelper.current
    val uiState by signViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        signViewModel.sideEffect.collect {
            analyticsHelper.d(message = "Collected sideEffect = $it")
            when (it) {
                is SignSideEffect.NavigateToTermsOfUse -> navigateToTermsOfUse()

                is SignSideEffect.NavigateToPrivacyPolicy -> navigateToPrivacyPolicy()

                is SignSideEffect.NavigateToSignUp -> {
                    val nickname = it.nickname

                    if (nickname == null) {
                        navigateToNickname()
                        return@collect
                    }

                    when (nickname.isEmpty()) {
                        true -> navigateToNickname()

                        false -> navigateToBirthDate(nickname)
                    }
                }

                is SignSideEffect.PatchInfo,
                is SignSideEffect.NavigateToMain,
                is SignSideEffect.GoogleSignIn,
                is SignSideEffect.KakaoSignIn,
                is SignSideEffect.SuccessSocialSign -> Unit
            }
        }
    }

    when (uiState.isLoading) {
        true -> SignInLoadingScreen()

        false -> SignInDefaultScreen(
            onClickGoogleSignIn = { signViewModel.intent(SignIntent.OnClickSignGoogle) },
            onClickKakaoSignIn = { signViewModel.intent(SignIntent.OnClickSignKakao) },
            onClickTermsOfUse = { signViewModel.intent(SignIntent.OnClickTermsOfUse) },
            onClickGuestMode = { signViewModel.intent(SignIntent.OnClickGuestMode) },
            onClickToPrivacyPolicy = { signViewModel.intent(SignIntent.OnClickPrivacyPolicy) }
        )
    }

}