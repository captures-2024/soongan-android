package com.captures2024.soongan

import android.graphics.Color
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
import com.captures2024.soongan.feature.signIn.SignInViewModel
import com.captures2024.soongan.feature.signIn.state.SignInIntent
import com.captures2024.soongan.feature.signIn.state.SignInSideEffect
import com.captures2024.soongan.route.AppRoute
import com.captures2024.soongan.state.AppRootIntent
import com.captures2024.soongan.state.AppRootSideEffect
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.messaging.FirebaseMessaging
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
    private val signInViewModel: SignInViewModel by viewModels()

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
                signInViewModel.sideEffect.collect { sideEffect ->
                    analyticsHelper.d(
                        LogElementArgument("signInVm.sideEffect", "signInViewModel.sideEffect = $sideEffect"),
                        message = "Collected sideEffect"
                    )

                    when (sideEffect) {
                        is SignInSideEffect.GoogleSignIn -> signInGoogle(launcher)

                        is SignInSideEffect.KakaoSignIn -> signInKakao()

                        is SignInSideEffect.SuccessSocialSign -> successSocialSign()

                        is SignInSideEffect.NavigateToMain -> navigateToMain()

                        is SignInSideEffect.NavigateToSignUp,
                        is SignInSideEffect.NavigateToPrivacyPolicy,
                        is SignInSideEffect.NavigateToTermsOfUse -> Unit
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
                        is AppRootSideEffect.FetchFcmToken -> fetchFcmToken(sideEffect.token)

                        is AppRootSideEffect.FailedRemoteSyncData -> failedSyncData()

                        is AppRootSideEffect.SuccessRemoteSyncData -> successSyncData(sideEffect)
                    }
                }
            }

            CompositionLocalProvider(LocalAnalyticsHelper provides analyticsHelper) {
                SoonGanTheme(darkTheme = darkTheme) {
                    AppRoute(
                        appRootViewModel = appRootViewModel,
                        signInViewModel = signInViewModel,
                    )
                }
            }
        }
    }

    private fun initFcmToken() {
        FirebaseMessaging.getInstance()
            .token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    analyticsHelper.e(
                        throwable = task.exception,
                        message = "Fetching FCM registration token failed"
                    )
                    return@addOnCompleteListener
                }

                val token = task.result

                appRootViewModel.intent(AppRootIntent.FetchFCMToken(token = token))
            }
    }

    private fun onResult(result: ActivityResult) {
        when (result.resultCode) {
            RESULT_OK -> {
                val resultIntent = result.data

                if (resultIntent == null) {
                    signInViewModel.intent(SignInIntent.FailedSignGoogle)
                    return
                }

                val token = googleAuthUiClient.signInWithIntent(resultIntent)

                if (token == null) {
                    signInViewModel.intent(SignInIntent.FailedSignGoogle)
                    return
                }

                signInViewModel.intent(SignInIntent.CompleteSignGoogle(token = token))
            }

            RESULT_CANCELED -> {
                signInViewModel.intent(SignInIntent.CanceledSignGoogle)
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

        signInViewModel.intent(
            SignInIntent.CompleteSignKakao(
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

        signInViewModel.intent(SignInIntent.FailedSignKakao)
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
        appRootViewModel.intent(AppRootIntent.SuccessSign)
    }

    private fun fetchFcmToken(fcmToken: String) {
        signInViewModel.intent(SignInIntent.FetchFcmToken(fcmToken))
    }

    private fun failedSyncData() {
        signInViewModel.intent(SignInIntent.FailedSyncData)
    }

    private fun successSyncData(sideEffect: AppRootSideEffect.SuccessRemoteSyncData) {
        signInViewModel.intent(
            SignInIntent.SuccessSyncData(
                nickname = sideEffect.nickname,
                birthDate = sideEffect.birthDate,
            )
        )
    }

    private fun navigateToMain() {
        appRootViewModel.intent(AppRootIntent.NavigateToMain)
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