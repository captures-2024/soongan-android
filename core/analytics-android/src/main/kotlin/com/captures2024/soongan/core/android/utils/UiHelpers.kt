package com.captures2024.soongan.core.android.utils

import androidx.compose.runtime.staticCompositionLocalOf
import com.captures2024.soongan.core.analytics.DefaultAnalyticsHelper
import com.captures2024.soongan.core.analytics.helper.AnalyticsHelper

val LocalAnalyticsHelper = staticCompositionLocalOf<AnalyticsHelper> {
    DefaultAnalyticsHelper()
}
