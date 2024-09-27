package com.captures2024.soongan.core.android.utils

import androidx.compose.runtime.staticCompositionLocalOf
import com.captures2024.soongan.core.android.helper.AnalyticsHelper
import com.captures2024.soongan.core.android.helper.impl.DefaultAnalyticsHelper

val LocalAnalyticsHelper = staticCompositionLocalOf<AnalyticsHelper> {
    DefaultAnalyticsHelper()
}
