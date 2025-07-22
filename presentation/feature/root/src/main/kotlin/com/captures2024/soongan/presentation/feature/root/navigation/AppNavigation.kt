package com.captures2024.soongan.presentation.feature.root.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper
import com.captures2024.soongan.core.android.utils.LocalAnalyticsHelper
import com.captures2024.soongan.presentation.designsystem.ui.theme.SGTheme
import com.captures2024.soongan.presentation.feature.root.route.AppRoute
import com.captures2024.soongan.presentation.viewmodel.AppViewModel

@Composable
fun AppNavigation(
    analyticsHelper: AnalyticsHelper,
    darkTheme: Boolean,
    appViewModel: AppViewModel,
) {
    CompositionLocalProvider(
        LocalAnalyticsHelper provides analyticsHelper,
    ) {
        SGTheme(darkTheme = darkTheme) {
            AppRoute(
                viewModel = appViewModel,
            )
        }
    }
}