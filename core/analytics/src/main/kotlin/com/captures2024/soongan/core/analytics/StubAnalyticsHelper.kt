package com.captures2024.soongan.core.analytics

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

/**
 * An implementation of AnalyticsHelper just writes the events to logcat. Used in builds where no
 * analytics events should be sent to a backend.
 */
@Singleton
internal class StubAnalyticsHelper @Inject constructor() : AnalyticsHelper {
    override fun logEvent(event: AnalyticsEvent) {
        Log.d(TAG, "Received analytics event: $event")
    }

    companion object {
        private const val TAG = "StubAnalyticsHelper"
    }
}