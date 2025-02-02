package com.captures2024.soongan

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.auth.kakao.KakaoAuthHelper
import com.captures2024.soongan.core.auth.kakao.KakaoAuthHelperImpl
import com.captures2024.soongan.core.auth.kakao.KakaoLoginCallback
import com.captures2024.soongan.core.designsystem.theme.SGTheme
import com.captures2024.soongan.core.viewmodel.AppRootViewModel
import com.captures2024.soongan.core.viewmodel.sign.SignViewModel
import com.captures2024.soongan.route.AppRoute
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SoonGanActivity : ComponentActivity(), KakaoLoginCallback {

    //region di property
    @Inject
    lateinit var analyticsHelper: AnalyticsHelper
    //endregion

    private val appRootViewModel: AppRootViewModel by viewModels()
    private val signViewModel: SignViewModel by viewModels()

    private val kakaoAuthHelper: KakaoAuthHelper by lazy { KakaoAuthHelperImpl() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        analyticsHelper.d(message = "entry onCreate")

        setContent {
//            val darkTheme = isSystemInDarkTheme()
            val darkTheme = false

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
            }

            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        lightScrim = Color.TRANSPARENT,
                        darkScrim = Color.TRANSPARENT,
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim = lightScrim,
                        darkScrim = darkScrim,
                    ) { darkTheme },
                )
                onDispose {}
            }

            LaunchedEffect(Unit) {
                signViewModel.sideEffect.collect { sideEffect ->
                    analyticsHelper.d(
                        LogElementArgument("signInVm.sideEffect", "signInViewModel.sideEffect = $sideEffect"),
                        message = "Collected sideEffect"
                    )

                    when (sideEffect) {
                        is SignViewModel.Effect.KakaoSignIn -> signInKakao()

                        is SignViewModel.Effect.NavigateToSignUp,
                        is SignViewModel.Effect.NavigateToPrivacyPolicy,
                        is SignViewModel.Effect.NavigateToTermsOfUse -> Unit
                    }
                }
            }

            CompositionLocalProvider(LocalAnalyticsHelper provides analyticsHelper) {
                SGTheme(darkTheme = darkTheme) {
                    AppRoute(
                        appRootViewModel = appRootViewModel,
                        signViewModel = signViewModel,
                    )
                }
            }
        }
    }

    override fun onSuccessKakaoLogin(
        accessToken: String?,
        refreshToken: String?,
    ) {
        analyticsHelper.d(
            LogElementArgument("accessToken", accessToken.toString()),
            LogElementArgument("refreshToken", refreshToken.toString()),
            message = "onSuccessKakaoLogin"
        )

        signViewModel.intent(
            SignViewModel.Intent.CompleteSignKakao(
                accessToken = accessToken ?: "",
                refreshToken = refreshToken ?: "",
            )
        )
    }

    override fun onFailureKakaoLogin(
        error: Throwable?,
    ) {
        analyticsHelper.e(
            throwable = error,
            message = "onFailureKakaoLogin",
        )
    }

    private fun signInKakao() {
        kakaoAuthHelper.kakaoLogin(
            context = this,
            callback = this,
        )
    }
}

/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)