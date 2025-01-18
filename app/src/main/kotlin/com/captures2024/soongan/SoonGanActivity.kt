package com.captures2024.soongan

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.analytics.utils.LogElementArgument
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.auth.GoogleAuthUiClient
import com.captures2024.soongan.core.auth.kakao.KakaoAuthHelper
import com.captures2024.soongan.core.auth.kakao.KakaoAuthHelperImpl
import com.captures2024.soongan.core.auth.kakao.KakaoLoginCallback
import com.captures2024.soongan.core.designsystem.theme.SoonGanTheme
import com.captures2024.soongan.core.viewmodel.AppRootViewModel
import com.captures2024.soongan.core.viewmodel.sign.SignViewModel
import com.captures2024.soongan.route.AppRoute
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SoonGanActivity : ComponentActivity(), KakaoLoginCallback {

    //region di property
    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    @Inject
    lateinit var beginSignInRequest: BeginSignInRequest
    //endregion

    private val appRootViewModel: AppRootViewModel by viewModels()
    private val signViewModel: SignViewModel by viewModels()

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            oneTapClient = Identity.getSignInClient(this),
            signInRequest = beginSignInRequest
        )
    }

    private val kakaoAuthHelper: KakaoAuthHelper by lazy { KakaoAuthHelperImpl() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        analyticsHelper.d(message = "entry onCreate")

        setContent {
            val darkTheme = isSystemInDarkTheme()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = this::onResult
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
            }

            initFcmToken()

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
                        is SignViewModel.Effect.GoogleSignIn -> signInGoogle(launcher)

                        is SignViewModel.Effect.KakaoSignIn -> signInKakao()

                        is SignViewModel.Effect.SuccessSocialSign -> successSocialSign()

                        is SignViewModel.Effect.NavigateToMain -> navigateToMain()

                        is SignViewModel.Effect.PatchInfo -> patchInfo(sideEffect)

                        is SignViewModel.Effect.NavigateToSignUp,
                        is SignViewModel.Effect.NavigateToPrivacyPolicy,
                        is SignViewModel.Effect.NavigateToTermsOfUse -> Unit
                    }
                }
            }

            LaunchedEffect(Unit) {
                appRootViewModel.sideEffect.collect { sideEffect ->
                    analyticsHelper.d(
                        LogElementArgument("appRootVm.sideEffect", "appRootVm.sideEffect = $sideEffect"),
                        message = "Collected sideEffect"
                    )

                    when (sideEffect) {
                        is AppRootViewModel.Effect.FailedRemoteSyncData -> failedSyncData()

                        is AppRootViewModel.Effect.SuccessRemoteSyncData -> successSyncData(sideEffect)
                    }
                }
            }

            CompositionLocalProvider(LocalAnalyticsHelper provides analyticsHelper) {
                SoonGanTheme(darkTheme = darkTheme) {
                    AppRoute(
                        appRootViewModel = appRootViewModel,
                        signViewModel = signViewModel,
                    )
                }
            }
        }
    }

    private fun initFcmToken() {
        appRootViewModel.intent(AppRootViewModel.Intent.FetchFCMToken)
    }

    private fun onResult(result: ActivityResult) {
        when (result.resultCode) {
            RESULT_OK -> {
                val resultIntent = result.data

                if (resultIntent == null) {
                    signViewModel.intent(SignViewModel.Intent.FailedSignGoogle)
                    return
                }

                val token = googleAuthUiClient.signInWithIntent(resultIntent)

                if (token == null) {
                    signViewModel.intent(SignViewModel.Intent.FailedSignGoogle)
                    return
                }

                signViewModel.intent(SignViewModel.Intent.CompleteSignGoogle(token = token))
            }

            RESULT_CANCELED -> {
                signViewModel.intent(SignViewModel.Intent.CanceledSignGoogle)
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

        signViewModel.intent(SignViewModel.Intent.FailedSignKakao)
    }

    private fun signInGoogle(launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) = lifecycleScope.launch {
        val signInIntentSender = googleAuthUiClient.signIn() ?: return@launch

        launcher.launch(IntentSenderRequest.Builder(signInIntentSender).build())
    }

    private fun signInKakao() {
        kakaoAuthHelper.kakaoLogin(
            context = this,
            callback = this,
        )
    }

    private fun successSocialSign() {
        appRootViewModel.intent(AppRootViewModel.Intent.SuccessSign)
    }

    private fun failedSyncData() {
        signViewModel.intent(SignViewModel.Intent.FailedSyncData)
    }

    private fun successSyncData(sideEffect: AppRootViewModel.Effect.SuccessRemoteSyncData) {
        signViewModel.intent(
            SignViewModel.Intent.SuccessSyncData(
                nickname = sideEffect.nickname,
                birthYear = sideEffect.birthYear,
            )
        )
    }

    private fun navigateToMain() {
        appRootViewModel.intent(AppRootViewModel.Intent.NavigateToMain)
    }

    private fun patchInfo(sideEffect: SignViewModel.Effect.PatchInfo) {
        appRootViewModel.intent(
            AppRootViewModel.Intent.PatchMemberInfo(
                nickname = sideEffect.nickname,
                birthYear = sideEffect.birthYear,
            )
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