package com.captures2024.soongan.feature.sign

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.lifecycleScope
import com.captures2024.soongan.core.analytics.AnalyticsHelper
import com.captures2024.soongan.core.analytics.LocalAnalyticsHelper
import com.captures2024.soongan.core.analytics.NetworkMonitor
import com.captures2024.soongan.core.auth.GoogleAuthUiClient
import com.captures2024.soongan.core.auth.kakao.KakaoLoginCallback
import com.captures2024.soongan.core.auth.kakaoLogin
import com.captures2024.soongan.core.designsystem.theme.SoonGanTheme
import com.captures2024.soongan.core.navigator.MainNavigator
import com.captures2024.soongan.feature.sign.route.SignRoute
import com.captures2024.soongan.feature.signIn.SignInViewModel
import com.captures2024.soongan.feature.signIn.state.SignInIntent
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SignActivity : ComponentActivity() {

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    @Inject
    lateinit var beginSignInRequest: BeginSignInRequest

    @Inject
    lateinit var mainNavigator: MainNavigator

    private val signInViewModel: SignInViewModel by viewModels()

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            oneTapClient = Identity.getSignInClient(this),
            signInRequest = beginSignInRequest
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Timber.tag(TAG).e(task.exception, "Fetching FCM registration token failed")
                    return@OnCompleteListener
                }

                val token = task.result

                signInViewModel.intent(SignInIntent.FetchFCMToken(token = token))
            }
        )

        setContent {
            val darkTheme = isSystemInDarkTheme()

            val kakaoLoginCallback = object : KakaoLoginCallback {

                override fun onSuccess(
                    accessToken: String?,
                    refreshToken: String?
                ) {
                    signInViewModel.intent(SignInIntent.CompleteSignKakao(accessToken ?: "", refreshToken ?: ""))
                }

                override fun onFailure(error: Throwable?) {
                    signInViewModel.intent(SignInIntent.FailedSignKakao)
                }

            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    when (result.resultCode) {
                        RESULT_OK -> {
                            val resultIntent = result.data

                            if (resultIntent == null) {
                                signInViewModel.intent(SignInIntent.FailedSignGoogle)
                                return@rememberLauncherForActivityResult
                            }

                            val token = googleAuthUiClient.signInWithIntent(resultIntent)

                            if (token == null) {
                                signInViewModel.intent(SignInIntent.FailedSignGoogle)
                                return@rememberLauncherForActivityResult
                            }

                            signInViewModel.intent(SignInIntent.CompleteSignGoogle(token = token))
                        }

                        RESULT_CANCELED -> signInViewModel.intent(SignInIntent.CanceledSignGoogle)
                    }
                }
            )

            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT,
                        Color.TRANSPARENT,
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim,
                        darkScrim,
                    ) { darkTheme },
                )
                onDispose {}
            }

            CompositionLocalProvider(LocalAnalyticsHelper provides analyticsHelper) {
                SoonGanTheme(
                    darkTheme = darkTheme,
                    androidTheme = false,
                    disableDynamicTheming = false,
                ) {
                    SignRoute(
                        networkMonitor = networkMonitor,
                        appleSignIn = {
                            TODO("Apple Sign In Logic")
                        },
                        googleSignIn = {
                            lifecycleScope.launch {
                                val signInIntentSender = googleAuthUiClient.signIn() ?: return@launch

                                launcher.launch(IntentSenderRequest.Builder(signInIntentSender).build())
                            }
                        },
                        kakaoSignIn = {
                            kakaoLogin(
                                context = this,
                                callback = kakaoLoginCallback
                            )
                        },
                        navigateToMain = { isGuestMode ->
                            mainNavigator.navigateFrom(
                                activity = this,
                                withFinish = true,
                                intentBuilder = {
                                    putExtra(GUEST_MODE_KEY, isGuestMode)
                                }
                            )
                        },
                        signInViewModel = signInViewModel
                    )
                }
            }
        }
    }

    companion object {
        private const val TAG = "SignActivity"
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