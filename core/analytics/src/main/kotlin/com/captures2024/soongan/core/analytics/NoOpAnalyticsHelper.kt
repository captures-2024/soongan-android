package com.captures2024.soongan.core.analytics

import com.captures2024.soongan.core.analytics.AnalyticsEvent
import com.captures2024.soongan.core.analytics.AnalyticsHelper

/**
 * Implementation of AnalyticsHelper which does nothing. Useful for tests and previews.
 */
class NoOpAnalyticsHelper : AnalyticsHelper {
    override fun logEvent(event: AnalyticsEvent) = Unit
}