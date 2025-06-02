package com.captures2024.soongan

import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsCallback
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.core.net.toUri
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.core.common.extension.toMap
import com.captures2024.soongan.core.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.core.model.AppConst
import com.captures2024.soongan.presentation.viewmodel.AppViewModel
import com.captures2024.soongan.route.AppRoute
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SoonGanActivity : ComponentActivity() {

    //region di property
    @Inject
    lateinit var analyticsHelper: AnalyticsHelper
    //endregion

    private val appViewModel: AppViewModel by viewModels()

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

    private val customTabs = object : CustomTabsServiceConnection() {
        override fun onCustomTabsServiceConnected(
            name: ComponentName,
            client: CustomTabsClient,
        ) {
            analyticsHelper.i { "PlatformInAppBrowserController::onCustomTabsServiceConnected - name: $name, client: $client" }
            client.warmup(0)

            val session = client.newSession(CustomTabsCallback())
            session?.mayLaunchUrl(currentUrl.toUri(), null, null)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            analyticsHelper.i { "PlatformInAppBrowserController::onServiceDisconnected - name: $name" }
        }
    }

    private var currentUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        analyticsHelper.d { "entry onCreate" }

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

            CompositionLocalProvider(
                LocalAnalyticsHelper provides analyticsHelper,
            ) {
                LaunchedEffect(appViewModel.sideEffect) {
                    appViewModel.sideEffect.collect { effect ->
                        when (effect) {
                            is AppViewModel.Effect.OpenInAppBrowser -> launchInAppBrowser(effect.url)
                            is AppViewModel.Effect.ShowSingleButtonDialog -> Unit
                        }
                    }
                }

                SGTheme(darkTheme = darkTheme) {
                    AppRoute(
                        viewModel = appViewModel,
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val localIntent = intent
        val action = localIntent?.action
        val extras = localIntent?.extras
        val data = localIntent?.data
        val parameters = data?.queryParameterNames?.associate { it to localIntent.data?.getQueryParameter(it) }
        analyticsHelper.d { "[PUSH] onResume - $localIntent, $action, $extras, $parameters" }

        if (localIntent?.action?.equals(AppConst.Notification.PUSH_ACTION_NAME, ignoreCase = true) == true) {
            analyticsHelper.d { "push - localIntent?.action: ${localIntent.action}" }
            val payload = extras?.toMap()?.map { it.key to it.value.toString() }?.toMap() ?: emptyMap()
            appViewModel.intent(AppViewModel.Intent.PostNotification(payload))
            intent = null
        }
    }

    override fun onNewIntent(newIntent: Intent) {
        super.onNewIntent(newIntent)
        analyticsHelper.v { "[PUSH] onNewIntent - intent: ${intent?.extras} newIntent: ${newIntent?.extras}" }
        intent = newIntent
    }

    override fun onDestroy() {
        disposeInAppBrowser()
        super.onDestroy()
    }

    private fun launchInAppBrowser(url: String) {
        currentUrl = url
        val intent = Intent(Intent.ACTION_VIEW, currentUrl.toUri())

        // 설치된 브라우저 앱이 없는 경우
        val packages = this.packageManager?.queryIntentActivities(intent, 0)
            ?.map { it.activityInfo.packageName }
            ?: emptyList()

        if (packages.isEmpty()) {
            analyticsHelper.w { "launch - packages is empty" }
            return
        }

        val customTabsPackageName = CustomTabsClient.getPackageName(this, packages, true)

        if (customTabsPackageName.isNullOrBlank()) {
            analyticsHelper.w { "launch - customTabsPackageName is null or blank" }
            return
        }

        CustomTabsClient.bindCustomTabsService(this, customTabsPackageName, customTabs)

        val tabsIntent = CustomTabsIntent.Builder()
            .setShowTitle(true)
            .setShareState(CustomTabsIntent.SHARE_STATE_OFF)
            .build()

        tabsIntent.launchUrl(this, currentUrl.toUri())
    }

    private fun disposeInAppBrowser() {
        if (currentUrl.isEmpty()) {
            return
        }
        this.unbindService(customTabs)
        currentUrl = ""
    }
}
