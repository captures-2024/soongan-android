package com.captures2024.soongan.core.navigator.activity

import android.app.Activity
import android.content.Intent

interface ActivityNavigateHelper {
    fun navigateFrom(
        activity: Activity,
        withFinish: Boolean,
        intentBuilder: Intent.() -> Intent = { this },
    )
}