package com.captures2024.soongan.feature.navigator

import android.app.Activity
import android.content.Intent

interface NavigateHelper {
    fun navigateFrom(
        activity: Activity,
        withFinish: Boolean,
        intentBuilder: Intent.() -> Intent = { this },
    )
}