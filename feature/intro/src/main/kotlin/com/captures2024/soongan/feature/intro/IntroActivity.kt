package com.captures2024.soongan.feature.intro

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.analytics.AnalyticsHelper
import com.captures2024.soongan.core.analytics.LocalAnalyticsHelper
import com.captures2024.soongan.core.analytics.NetworkMonitor
import com.captures2024.soongan.core.designsystem.theme.SoonGanTheme
import com.captures2024.soongan.feature.intro.route.IntroRoute
import com.captures2024.soongan.feature.navigator.SignNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroActivity : ComponentActivity() {

    @Inject
    lateinit var signNavigator: SignNavigator

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    private val viewModel: IntroActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            val uiState: IntroActivityUiState by viewModel.uiState.collectAsStateWithLifecycle()

            val darkTheme = ShouldUseDarkTheme(uiState)

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
                    androidTheme = ShouldUseAndroidTheme(uiState),
                    disableDynamicTheming = ShouldDisableDynamicTheming(uiState),
                ) {
                    IntroRoute(
                        uiState = uiState,
                        networkMonitor = networkMonitor,
                        navigateToSign = {
                            signNavigator.navigateFrom(
                                activity = this,
                                withFinish = true,
                            )
                        },
                        navigateToMain = {

                        }
                    )
                }
            }
        }

    }

}

/**
 * Returns `true` if the Android theme should be used, as a function of the [uiState].
 */
@Composable
private fun ShouldUseAndroidTheme(
    uiState: IntroActivityUiState,
): Boolean = when (uiState) {
    IntroActivityUiState.Loading -> false
    IntroActivityUiState.Success -> false
}

/**
 * Returns `true` if the dynamic color is disabled, as a function of the [uiState].
 */
@Composable
private fun ShouldDisableDynamicTheming(
    uiState: IntroActivityUiState,
): Boolean = when (uiState) {
    IntroActivityUiState.Loading -> false
    IntroActivityUiState.Success -> false
}


/**
 * Returns `true` if dark theme should be used, as a function of the [uiState] and the
 * current system context.
 */
@Composable
private fun ShouldUseDarkTheme(
    uiState: IntroActivityUiState,
): Boolean = when (uiState) {
    IntroActivityUiState.Loading -> isSystemInDarkTheme()
    IntroActivityUiState.Success -> isSystemInDarkTheme()
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