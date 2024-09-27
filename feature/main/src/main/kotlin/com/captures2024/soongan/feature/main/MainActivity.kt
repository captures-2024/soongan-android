package com.captures2024.soongan.feature.main

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.captures2024.soongan.core.android.helper.AnalyticsHelper
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.android.helper.NetworkMonitor
import com.captures2024.soongan.core.designsystem.theme.SoonGanTheme
import com.captures2024.soongan.feature.main.route.MainRoute
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity()  {

    @Inject
    lateinit var analyticsHelper: AnalyticsHelper

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setUpGuestMode(this.intent.getBooleanExtra("isGuestMode", false))

        setContent {
            val uiState: MainActivityUiState by viewModel.uiState.collectAsStateWithLifecycle()

            val darkTheme = ShouldUseDarkTheme(uiState)

            DisposableEffect(darkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT,
                        Color.TRANSPARENT,
                    ) { darkTheme },
                    navigationBarStyle = SystemBarStyle.light(Color.WHITE, Color.WHITE),
                )
                onDispose {}
            }

            CompositionLocalProvider(LocalAnalyticsHelper provides analyticsHelper) {
                SoonGanTheme(
                    darkTheme = darkTheme,
                    androidTheme = ShouldUseAndroidTheme(uiState),
                    disableDynamicTheming = ShouldDisableDynamicTheming(uiState),
                ) {
                    when (val state = uiState) {
                        is MainActivityUiState.Success -> MainRoute(
                            isGuestMode = state.isGuestMode,
                            networkMonitor = networkMonitor
                        )
                        else -> Unit
                    }
                }
            }
        }

    }

    companion object {
        private const val TAG = "MainAct"
    }
}

/**
 * Returns `true` if the Android theme should be used, as a function of the [uiState].
 */
@Composable
private fun ShouldUseAndroidTheme(
    uiState: MainActivityUiState,
): Boolean = when (uiState) {
    is MainActivityUiState.Loading -> false
    is MainActivityUiState.Success -> false
}

/**
 * Returns `true` if the dynamic color is disabled, as a function of the [uiState].
 */
@Composable
private fun ShouldDisableDynamicTheming(
    uiState: MainActivityUiState,
): Boolean = when (uiState) {
    is MainActivityUiState.Loading -> false
    is MainActivityUiState.Success -> false
}


/**
 * Returns `true` if dark theme should be used, as a function of the [uiState] and the
 * current system context.
 */
@Composable
private fun ShouldUseDarkTheme(
    uiState: MainActivityUiState,
): Boolean = when (uiState) {
    is MainActivityUiState.Loading -> isSystemInDarkTheme()
    is MainActivityUiState.Success -> isSystemInDarkTheme()
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
