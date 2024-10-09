package com.captures2024.soongan.feature.sign

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
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.android.helper.NetworkMonitor
import com.captures2024.soongan.core.auth.GoogleAuthUiClient
import com.captures2024.soongan.core.auth.kakao.KakaoAuthHelper
import com.captures2024.soongan.core.auth.kakao.KakaoAuthHelperImpl
import com.captures2024.soongan.core.auth.kakao.KakaoLoginCallback
import com.captures2024.soongan.core.designsystem.theme.SoonGanTheme
import com.captures2024.soongan.core.navigator.activity.MainActivityNavigator
import com.captures2024.soongan.feature.sign.route.SignRoute
import com.captures2024.soongan.feature.sign.ui.AppleWebView
import com.captures2024.soongan.feature.signIn.SignInViewModel
import com.captures2024.soongan.feature.signIn.state.SignInIntent
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignActivity : ComponentActivity(), KakaoLoginCallback {

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    @Inject
    lateinit var beginSignInRequest: BeginSignInRequest

    @Inject
    lateinit var mainActivityNavigator: MainActivityNavigator

    private val signInViewModel: SignInViewModel by viewModels()
    private val appleSignInViewModel: AppleSignInViewModel by viewModels()

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            oneTapClient = Identity.getSignInClient(this),
            signInRequest = beginSignInRequest
        )
    }

    private val kakaoAuthHelper: KakaoAuthHelper by lazy { KakaoAuthHelperImpl() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    analyticsHelper.e(
                        throwable = task.exception,
                        message = "Fetching FCM registration token failed"
                    )
                    return@OnCompleteListener
                }

                val token = task.result

                signInViewModel.intent(SignInIntent.FetchFCMToken(token = token))
            }
        )

        setContent {
            val darkTheme = isSystemInDarkTheme()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = this::onResult
            )

            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        lightScrim = Color.TRANSPARENT,
                        darkScrim = Color.TRANSPARENT,
                    ) {
                        darkTheme
                    },
                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim = lightScrim,
                        darkScrim = darkScrim,
                    ) {
                        darkTheme
                    },
                )
                onDispose {}
            }

            CompositionLocalProvider(LocalAnalyticsHelper provides analyticsHelper) {
                SoonGanTheme(
                    darkTheme = darkTheme,
                    androidTheme = false,
                    disableDynamicTheming = false,
                ) {
                    val appleState by appleSignInViewModel.appleSignInState.collectAsStateWithLifecycle()

                    when (appleState) {
                        is AppleSignInState.Init -> AppleWebView(
                            onSuccess = {},
                            onFailure = {}
                        )

                        else -> SignRoute(
                            networkMonitor = networkMonitor,
                            appleSignIn = { appleSignInViewModel.onClickAppleSignIn() },
                            googleSignIn = { signInWithGoogle(launcher) },
                            kakaoSignIn = { kakaoAuthHelper.kakaoLogin(context = this, callback = this) },
                            navigateToMain = this::navigateToMain,
                            signInViewModel = signInViewModel,
                        )
                    }

                }
            }
        }
    }

    override fun onSuccessKakaoLogin(
        accessToken: String?,
        refreshToken: String?,
    ) {
        signInViewModel.intent(
            SignInIntent.CompleteSignKakao(
                accessToken = accessToken ?: "",
                refreshToken = refreshToken ?: "",
            )
        )
    }

    override fun onFailureKakaoLogin(error: Throwable?) {
        signInViewModel.intent(SignInIntent.FailedSignKakao)
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

            RESULT_CANCELED -> signInViewModel.intent(SignInIntent.CanceledSignGoogle)
        }
    }

    private fun signInWithGoogle(launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) {
        lifecycleScope.launch {
            val signInIntentSender = googleAuthUiClient.signIn() ?: return@launch

            launcher.launch(IntentSenderRequest.Builder(signInIntentSender).build())
        }
    }

    private fun navigateToMain(isGuestMode: Boolean) {
        mainActivityNavigator.navigateFrom(
            activity = this,
            withFinish = true,
            intentBuilder = { putExtra(GUEST_MODE_KEY, isGuestMode) }
        )
    }

    companion object {
        const val GUEST_MODE_KEY = "isGuestMode"
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
